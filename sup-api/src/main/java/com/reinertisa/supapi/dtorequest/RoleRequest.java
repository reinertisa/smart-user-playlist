package com.reinertisa.supapi.dtorequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleRequest {
    @NotEmpty(message = "Role cannot be empy or null")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
