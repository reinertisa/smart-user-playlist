package com.reinertisa.supapi.service;

import com.reinertisa.supapi.entity.UserEntity;
import com.reinertisa.supapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;

    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }


}
