package com.workvenue.backend.core.util.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.workvenue.backend.data.request.login.LoginControllerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final AuthenticationProvider authenticationManager;

//    @Value("${app.jwtSecret}")
//    private String jwtSecret;
//
//    @Value("${app.jwtExpirationInMs}")
//    private int jwtExpirationInMs;

    public Authentication authenticate(LoginControllerRequest request) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
    }

    //JwtAuthenticationFilter
    public String generateAccessToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return JWT.create().withExpiresAt(
                        java.sql.Date.from(Instant.now().plus(6,
                                ChronoUnit.HOURS)))
//                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream()//TODO: roles util with authFilter
                        .map(GrantedAuthority::getAuthority)
                        .collect(
                                Collectors.toList()))
                .sign(Algorithm.HMAC256("secret".getBytes()));
    }
}
