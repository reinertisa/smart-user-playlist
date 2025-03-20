package com.reinertisa.supapi.security;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reinertisa.supapi.domain.ApiAuthentication;
import com.reinertisa.supapi.domain.Response;
import com.reinertisa.supapi.dto.User;
import com.reinertisa.supapi.dtorequest.LoginRequest;
import com.reinertisa.supapi.enumeration.LoginType;
import com.reinertisa.supapi.enumeration.TokenType;
import com.reinertisa.supapi.service.JwtService;
import com.reinertisa.supapi.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Map;

import static com.reinertisa.supapi.constant.Constants.LOGIN_PATH;
import static com.reinertisa.supapi.utils.RequestUtils.getResponse;
import static com.reinertisa.supapi.utils.RequestUtils.handleErrorResponse;

public class ApiAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final UserService userService;
    private final JwtService jwtService;

    public ApiAuthenticationFilter(AuthenticationManager authenticationManager,
                                   UserService userService, JwtService jwtService) {
        super(new AntPathRequestMatcher(LOGIN_PATH, HttpMethod.POST.name()), authenticationManager);
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        try {
            LoginRequest user = new ObjectMapper()
                    .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true)
                    .readValue(request.getInputStream(), LoginRequest.class);
            userService.updateLoginAttempt(user.getEmail(), LoginType.LOGIN_ATTEMPT);
            ApiAuthentication authentication = ApiAuthentication.unauthenticated(user.getEmail(), user.getPassword());
            return getAuthenticationManager().authenticate(authentication);
        } catch (Exception ex) {
            handleErrorResponse(request, response, ex);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication)
            throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        userService.updateLoginAttempt(user.getEmail(), LoginType.LOGIN_SUCCESS);
        Response httpResponse = user.isMfa() ? sendQrCode(request, user) : sendResponse(request, response, user);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        ServletOutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, httpResponse);
        out.flush();
    }

    private Response sendResponse(HttpServletRequest request, HttpServletResponse response, User user) {
        jwtService.addCookie(response, user, TokenType.ACCESS);
        jwtService.addCookie(response, user, TokenType.REFRESH);
        return getResponse(request, Map.of("user", user), "Login Success", HttpStatus.OK);
    }

    private Response sendQrCode(HttpServletRequest request, User user) {
        return getResponse(request, Map.of("user", user), "Please enter QR Code", HttpStatus.OK);
    }
}
