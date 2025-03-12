package com.reinertisa.supapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reinertisa.supapi.enumeration.Authority;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RoleEntity extends Auditable{
    private String name;
    private Authority authorities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Authority getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authority authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "name='" + name + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
