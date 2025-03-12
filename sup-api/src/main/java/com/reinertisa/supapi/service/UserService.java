package com.reinertisa.supapi.service;


import com.reinertisa.supapi.entity.RoleEntity;

public interface UserService {

    void createUser(String firstName, String lastName, String email, String password);

    RoleEntity getRoleName(String name);

}
