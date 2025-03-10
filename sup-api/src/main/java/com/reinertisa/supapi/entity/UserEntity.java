package com.reinertisa.supapi.entity;

import java.time.LocalDateTime;


public class UserEntity extends Auditable {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer loginAttempts;
    private LocalDateTime lastLogin;
    private String phone;
    private String bio;
    private String imageUrl;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean enabled;
    private boolean mfa;
    private String qrCodeSecret;
    private String qrCodeImageUri;
    private String roles; // TODO(reinertisa) create Role class and map here with JPA
}
