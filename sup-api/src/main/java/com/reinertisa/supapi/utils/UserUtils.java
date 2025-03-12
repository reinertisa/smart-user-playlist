package com.reinertisa.supapi.utils;

import com.reinertisa.supapi.entity.RoleEntity;
import com.reinertisa.supapi.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

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
}
