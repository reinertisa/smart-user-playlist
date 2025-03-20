package com.reinertisa.supapi.service;


import com.reinertisa.supapi.dto.User;
import com.reinertisa.supapi.entity.CredentialEntity;
import com.reinertisa.supapi.entity.RoleEntity;
import com.reinertisa.supapi.enumeration.LoginType;

public interface UserService {
    void createUser(String firstName, String lastName, String email, String password);
    RoleEntity getRoleName(String name);
    void verifyAccountKey(String key);
    void updateLoginAttempt(String email, LoginType loginType);
    User getUserByUserId(String userId);
    CredentialEntity getUserCredentialById(Long id);
}
