package com.reinertisa.supapi.handler;


import com.reinertisa.supapi.enumeration.TokenType;
import com.reinertisa.supapi.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;


@Service
public class ApiLogoutHandler implements LogoutHandler {
    private final JwtService jwtService;

    public ApiLogoutHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        jwtService.removeCookie(request, response, TokenType.ACCESS.getValue());
        jwtService.removeCookie(request, response, TokenType.REFRESH.getValue());
    }
}
