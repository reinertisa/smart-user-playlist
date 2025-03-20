package com.reinertisa.supapi.security;

import com.reinertisa.supapi.service.JwtService;
import com.reinertisa.supapi.service.UserService;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class ApiHttpConfigurer extends AbstractHttpConfigurer<ApiHttpConfigurer, HttpSecurity> {

    private final AuthorizationFilter authorizationFilter;
    private final ApiAuthenticationProvider apiAuthenticationProvider;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationConfiguration authenticationConfiguration;

    public ApiHttpConfigurer(AuthorizationFilter authorizationFilter,
                             ApiAuthenticationProvider apiAuthenticationProvider, UserService userService,
                             JwtService jwtService, AuthenticationConfiguration authenticationConfiguration) {
        this.authorizationFilter = authorizationFilter;
        this.apiAuthenticationProvider = apiAuthenticationProvider;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Override
    public void init(HttpSecurity http) throws Exception {
        http.authenticationProvider(apiAuthenticationProvider);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(
                new ApiAuthenticationFilter(
                        authenticationConfiguration.getAuthenticationManager(),
                        userService,
                        jwtService
                ),
                UsernamePasswordAuthenticationFilter.class
        );
    }

}
