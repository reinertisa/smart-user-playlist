package com.reinertisa.supapi.security;

import com.reinertisa.supapi.domain.ApiAuthentication;
import com.reinertisa.supapi.domain.RequestContext;
import com.reinertisa.supapi.domain.Token;
import com.reinertisa.supapi.domain.TokenData;
import com.reinertisa.supapi.dto.User;
import com.reinertisa.supapi.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static com.reinertisa.supapi.constant.Constants.PUBLIC_ROUTES;
import static com.reinertisa.supapi.enumeration.TokenType.ACCESS;
import static com.reinertisa.supapi.enumeration.TokenType.REFRESH;
import static com.reinertisa.supapi.utils.RequestUtils.handleErrorResponse;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public AuthorizationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            Optional<String> accessToken = jwtService.extractToken(request, ACCESS.getValue());
            if (accessToken.isPresent() && jwtService.getTokenData(accessToken.get(), TokenData::isValid)) {
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(accessToken.get(), request));
                RequestContext.setUserId(jwtService.getTokenData(accessToken.get(), TokenData::getUser).getId());
            } else {
                Optional<String> refreshToken = jwtService.extractToken(request, REFRESH.getValue());
                if (refreshToken.isPresent() && jwtService.getTokenData(refreshToken.get(), TokenData::isValid)) {
                    User user = jwtService.getTokenData(refreshToken.get(), TokenData::getUser);
                    SecurityContextHolder.getContext().setAuthentication(
                                    getAuthentication(jwtService.createToken(user, Token::getAccess), request));
                    jwtService.addCookie(response, user, ACCESS);
                    RequestContext.setUserId(user.getId());
                } else {
                    SecurityContextHolder.clearContext();
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            handleErrorResponse(request, response, ex);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        boolean shouldNotFilter = request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())
                || Arrays.asList(PUBLIC_ROUTES).contains(request.getRequestURI());
        if (shouldNotFilter) {
            RequestContext.setUserId(0L);
        }
        return shouldNotFilter;
    }

    private Authentication getAuthentication(String token, HttpServletRequest request) {
        ApiAuthentication authentication = ApiAuthentication.authenticated(jwtService.getTokenData(token, TokenData::getUser),
                jwtService.getTokenData(token, TokenData::getAuthorities));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }
}
