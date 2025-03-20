package com.reinertisa.supapi.security;

import com.reinertisa.supapi.domain.ApiAuthentication;
import com.reinertisa.supapi.domain.UserPrincipal;
import com.reinertisa.supapi.dto.User;
import com.reinertisa.supapi.entity.CredentialEntity;
import com.reinertisa.supapi.exception.ApiException;
import com.reinertisa.supapi.service.UserService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.reinertisa.supapi.constant.Constants.NINETY_DAYS;

@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public ApiAuthenticationProvider(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiAuthentication apiAuthentication = authenticationFunction.apply(authentication);
        User user = userService.getUserByEmail(apiAuthentication.getEmail());
        if (user != null) {
            CredentialEntity userCredential = userService.getUserCredentialById(user.getId());
            if (userCredential.getUpdatedAt().minusDays(NINETY_DAYS).isAfter(LocalDateTime.now())) {
                throw new ApiException("Credentials are expired. Please reset your password.");
            }
            UserPrincipal userPrincipal = new UserPrincipal(user, userCredential);
            validAccount.accept(userPrincipal);
            if (encoder.matches(apiAuthentication.getPassword(), userCredential.getPassword())) {
                return ApiAuthentication.authenticated(user, userPrincipal.getAuthorities());
            } else {
                throw new BadCredentialsException("Email and/or password incorrect. Please try again.");
            }
        }
        throw new ApiException("Unable to authenticate.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiAuthentication.class.isAssignableFrom(authentication);
    }

    private final Function<Authentication, ApiAuthentication> authenticationFunction =
            authentication -> (ApiAuthentication) authentication;

    private final Consumer<UserPrincipal> validAccount = userPrincipal -> {
        if (!userPrincipal.isAccountNonLocked()) {
            throw new LockedException("Your account ic currently locked.");
        }
        if (!userPrincipal.isEnabled()) {
            throw new DisabledException("Your account ic currently disabled.");
        }
        if (!userPrincipal.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("Your password has expired. Please update your password.");
        }
        if (!userPrincipal.isAccountNonExpired()) {
            throw new DisabledException("Your account has expired. Please contact administrator.");
        }
    };
}
