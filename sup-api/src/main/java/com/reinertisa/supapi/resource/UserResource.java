package com.reinertisa.supapi.resource;


import com.reinertisa.supapi.entity.UserEntity;
import com.reinertisa.supapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok().body(userService.createUser(userEntity));
    }

}
