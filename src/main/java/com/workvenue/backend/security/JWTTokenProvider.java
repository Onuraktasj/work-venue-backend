package com.workvenue.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.workvenue.backend.data.request.login.LoginControllerRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTTokenProvider {

    private static String jwtSecret = "qweqwe";
    private final AuthenticationProvider authenticationManager;

    public Authentication authenticate(final LoginControllerRequest request) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
    }

    public String generateAccessToken(final Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return JWT.create().withExpiresAt(
                        Date.from(Instant.now().plus(1,
                                ChronoUnit.HOURS)))
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(
                                Collectors.toList()))
                .sign(Algorithm.HMAC256(jwtSecret.getBytes()));
    }
}
