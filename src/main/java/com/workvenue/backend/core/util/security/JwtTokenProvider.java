package com.workvenue.backend.core.util.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

//    @Value("${app.jwtSecret}")
//    private String jwtSecret;
//
//    @Value("${app.jwtExpirationInMs}")
//    private int jwtExpirationInMs;

    //JwtAuthenticationFilter
    public String generateAccessToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return JWT.create().withExpiresAt(
                        java.sql.Date.from(Instant.now().plus(6,
                                ChronoUnit.HOURS)))
//                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(
                                Collectors.toList()))
                .sign(Algorithm.HMAC256("secret".getBytes()));
    }
    //JwtAuthenticationFilter silinebilir olacak. buraya taşıncak.
    //JwtAuthorizationFilter doFilterInternal ise direkt oradan kullanılabilir olcak.
    // oradaki filtera uygun şekilde token gönderilmeli.
}
