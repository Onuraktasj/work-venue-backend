package com.workvenue.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    // yetki ve token kontrolü, 2 aşama gibi düşünülebilir
    // request endpointe gelmeden buradan geçiyor.


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/login")) {
            filterChain.doFilter(request, response);
        } else {
            final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    final String token = authorizationHeader.split("Bearer ")[1];
                    final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    final JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    final DecodedJWT decodedJWT = jwtVerifier.verify(token);
                    final String username = decodedJWT.getSubject();
                    List<SimpleGrantedAuthority> authorities = Stream.of(decodedJWT.getClaim("roles").asArray(String.class))
                            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                } catch (Exception exception) {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), Map.of("message", exception.getMessage()));
                }
            }
        }
    }
}
