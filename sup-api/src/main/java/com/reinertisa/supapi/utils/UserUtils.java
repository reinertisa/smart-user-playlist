package com.reinertisa.supapi.utils;

import com.reinertisa.supapi.dto.User;
import com.reinertisa.supapi.entity.CredentialEntity;
import com.reinertisa.supapi.entity.RoleEntity;
import com.reinertisa.supapi.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.reinertisa.supapi.constant.Constants.NINETY_DAYS;

public class UserUtils {

    public static UserEntity createUserEntity(String firstName, String lastName, String email, RoleEntity role) {
        UserEntity user = new UserEntity();
        user.setUserId(UUID.randomUUID().toString());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLastLogin(LocalDateTime.now());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(false);
        user.setMfa(false);
        user.setLoginAttempts(0);
        user.setQrCodeSecret(StringUtils.EMPTY);
        user.setPhone(StringUtils.EMPTY);
        user.setBio(StringUtils.EMPTY);
        user.setImageUrl("https://cdn-icons-pgn.flaticon.com/512/149/149071.png");
        user.setRole(role);
        return user;
    }

    public static User fromUserEntity(UserEntity userEntity, RoleEntity role, CredentialEntity credentialEntity) {
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        user.setLastLogin(userEntity.getLastLogin().toString());
        user.setCredentialsNonExpired(isCredentialsNonExpired(credentialEntity));
        user.setCreatedAt(userEntity.getCreatedAt().toString());
        user.setUpdatedAt(userEntity.getUpdatedAt().toString());
        user.setRole(role.getName());
        user.setAuthorities(role.getAuthorities().getValue());
        return user;
    }

    private static boolean isCredentialsNonExpired(CredentialEntity credentialEntity) {
        return credentialEntity.getUpdatedAt().plusDays(NINETY_DAYS).isAfter(LocalDateTime.now());
    }
}
