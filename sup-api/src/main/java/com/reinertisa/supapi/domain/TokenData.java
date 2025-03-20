package com.reinertisa.supapi.domain;

import com.reinertisa.supapi.dto.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class TokenData {
    private User user;
    private Claims claims;
    private boolean valid;
    private List<GrantedAuthority> authorities;

    public TokenData() {
    }

    public TokenData(User user, Claims claims, boolean valid, List<GrantedAuthority> authorities) {
        this.user = user;
        this.claims = claims;
        this.valid = valid;
        this.authorities = authorities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
