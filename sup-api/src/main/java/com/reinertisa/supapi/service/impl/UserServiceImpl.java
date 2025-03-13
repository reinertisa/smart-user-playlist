package com.reinertisa.supapi.service.impl;

import com.reinertisa.supapi.cache.CacheStore;
import com.reinertisa.supapi.domain.RequestContext;
import com.reinertisa.supapi.entity.ConfirmationEntity;
import com.reinertisa.supapi.entity.CredentialEntity;
import com.reinertisa.supapi.entity.RoleEntity;
import com.reinertisa.supapi.entity.UserEntity;
import com.reinertisa.supapi.enumeration.Authority;
import com.reinertisa.supapi.enumeration.EventType;
import com.reinertisa.supapi.enumeration.LoginType;
import com.reinertisa.supapi.event.UserEvent;
import com.reinertisa.supapi.exception.ApiException;
import com.reinertisa.supapi.repository.ConfirmationRepository;
import com.reinertisa.supapi.repository.CredentialRepository;
import com.reinertisa.supapi.repository.RoleRepository;
import com.reinertisa.supapi.repository.UserRepository;
import com.reinertisa.supapi.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.reinertisa.supapi.utils.UserUtils.createUserEntity;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialRepository credentialRepository;
    private final ConfirmationRepository confirmationRepository;
    private final CacheStore<String, Integer> userCache;
    private final ApplicationEventPublisher publisher;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           CredentialRepository credentialRepository, ConfirmationRepository confirmationRepository,
                           CacheStore<String, Integer> userCache, ApplicationEventPublisher publisher) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.credentialRepository = credentialRepository;
        this.confirmationRepository = confirmationRepository;
        this.userCache = userCache;
        this.publisher = publisher;
    }


    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        UserEntity userEntity = userRepository.save(createNewUser(firstName, lastName, email));
        CredentialEntity credentialEntity = new CredentialEntity(userEntity, password);
        credentialRepository.save(credentialEntity);
        ConfirmationEntity confirmationEntity = new ConfirmationEntity(userEntity);
        confirmationRepository.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION, Map.of("key", confirmationEntity.getKey())));
    }

    @Override
    public RoleEntity getRoleName(String name) {
        return roleRepository.findByNameIgnoreCase(name).orElseThrow(() -> new ApiException("Role not found"));
    }

    @Override
    public void verifyAccountKey(String key) {

        ConfirmationEntity confirmationEntity = getUserConfirmation(key);
        UserEntity userEntity = getUserEntityByEmail(confirmationEntity.getUserEntity().getEmail());
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
        confirmationRepository.delete(confirmationEntity);
    }

    @Override
    public void updateLoginAttempt(String email, LoginType loginType) {
        UserEntity userEntity = getUserEntityByEmail(email);
        RequestContext.setUserId(userEntity.getId());
        switch (loginType) {
            case LOGIN_ATTEMPT -> {
                if (userCache.get(userEntity.getEmail()) == null) {
                    userEntity.setLoginAttempts(0);
                    userEntity.setAccountNonLocked(true);
                }
                userEntity.setLoginAttempts(userEntity.getLoginAttempts() + 1);
                userCache.put(userEntity.getEmail(), userEntity.getLoginAttempts());
                if (userCache.get(userEntity.getEmail()) > 5) {
                    userEntity.setAccountNonLocked(false);
                }
            }
            case LOGIN_SUCCESS -> {

            }
        }
        userRepository.save(userEntity);
    }

    private UserEntity getUserEntityByEmail(String email) {
        return userRepository
                .findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ApiException("User not found"));
    }

    private ConfirmationEntity getUserConfirmation(String key) {
        return confirmationRepository.findByKey(key).orElseThrow(() -> new ApiException("Confirmation key not found."));
    }

    private UserEntity createNewUser(String firstName, String lastName, String email) {
        RoleEntity role = getRoleName(Authority.USER.name());
        return createUserEntity(firstName, lastName, email, role);
    }
}
