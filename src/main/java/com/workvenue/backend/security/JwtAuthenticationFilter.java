package com.workvenue.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    //Kimlik Doğrulama, ilk aşama, bu kullanıcı var mı


    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws
                                                                              AuthenticationException {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException,
                                                                              ServletException {
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); //TODO:
        // vault veya passwordManager ile geçeceğiz.
        final String accessToken = JWT.create().withExpiresAt(
                                              Date.from(Instant.now().plus(6,
                                                                           ChronoUnit.HOURS)))
                                      .withIssuer(request.getRequestURL().toString())
                                      .withClaim("roles", user.getAuthorities().stream()
                                                              .map(GrantedAuthority::getAuthority)
                                                              .collect(
                                                                      Collectors.toList()))
                                      .sign(algorithm);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final String refreshToken = JWT.create().withExpiresAt(
                                               Date.from(Instant.now().plus(10,
                                                                            ChronoUnit.HOURS)))
                                       .withIssuer(request.getRequestURL().toString())
                                       .withClaim("roles", user.getAuthorities().stream()
                                                               .map(GrantedAuthority::getAuthority)
                                                               .collect(
                                                                       Collectors.toList()))
                                       .sign(algorithm);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),
                                      Map.of("access_token", accessToken, "refresh_token",
                                             refreshToken));
    }
}
