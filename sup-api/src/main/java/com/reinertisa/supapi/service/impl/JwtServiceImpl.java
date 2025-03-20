package com.reinertisa.supapi.service.impl;

import com.reinertisa.supapi.domain.Token;
import com.reinertisa.supapi.domain.TokenData;
import com.reinertisa.supapi.dto.User;
import com.reinertisa.supapi.enumeration.TokenType;
import com.reinertisa.supapi.service.JwtService;
import com.reinertisa.supapi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    private final UserService userService;

    public JwtServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String createToken(User user, Function<Token, String> tokenFunction) {
        return "";
    }

    @Override
    public Optional<String> extractToken(HttpServletRequest request, String tokenType) {
        return Optional.empty();
    }

    @Override
    public void addCookie(HttpServletResponse response, User user, TokenType type) {

    }

    @Override
    public <T> T getTokenData(String token, Function<TokenData, T> tokenFunction) {
        return null;
    }

    @Override
    public void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {

    }
}
