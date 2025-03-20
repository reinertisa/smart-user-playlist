package com.reinertisa.supapi.validation;

import com.reinertisa.supapi.entity.UserEntity;
import com.reinertisa.supapi.exception.ApiException;

public class UserValidation {

    public static void verifyAccountStatus(UserEntity user) {
        if (!user.isEnabled()) {
            throw new ApiException("User is disabled");
        }
        if (!user.isAccountNonExpired()) {
            throw new ApiException("Account is expired");
        }
        if (!user.isAccountNonLocked()) {
            throw new ApiException("Accoount is locked");
        }
    }
}
