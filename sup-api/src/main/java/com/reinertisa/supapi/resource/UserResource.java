package com.reinertisa.supapi.resource;


import com.reinertisa.supapi.domain.Response;
import com.reinertisa.supapi.dtorequest.UserRequest;
import com.reinertisa.supapi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.reinertisa.supapi.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> createUser(@RequestBody @Valid UserRequest user, HttpServletRequest request) {
        userService.createUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return ResponseEntity.created(URI.create("")).body(getResponse(request, emptyMap(),
                        "Account created. Check your email to enable your account.", HttpStatus.CREATED));
    }

    @GetMapping("/verify/account")
    public ResponseEntity<Response> verifyAccount(@RequestParam("key") String key, HttpServletRequest request) {
        userService.verifyAccountKey(key);
        return ResponseEntity.ok().body(getResponse(request, emptyMap(), "Account verified.", HttpStatus.OK));
    }
}
