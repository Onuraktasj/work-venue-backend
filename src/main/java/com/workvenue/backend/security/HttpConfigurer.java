package com.workvenue.backend.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class HttpConfigurer extends AbstractHttpConfigurer<HttpConfigurer,
        HttpSecurity> {
    //Yazdığımız filterların çalışması için.


    @Override
    public void init(HttpSecurity builder) throws Exception {
        //json üzerinden post göndermeye yarıyor. disableda kadar olan kısım.
        builder.cors().and().csrf().disable().authorizeHttpRequests((auth) -> {
            //gelen herşeyi auth et yani geçirme.
                   auth.anyRequest().authenticated();
               }).sessionManagement()
               //sistemin state tutmaması için.
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(HttpSecurity builder) {
        // contexte class alabiliriz buna bak.
        AuthenticationManager authenticationManager = builder.getSharedObject(
                AuthenticationManager.class);
        builder.addFilter(new JwtAuthenticationFilter(authenticationManager))
               .addFilterBefore(new JwtAuthorizationFilter(),
                                UsernamePasswordAuthenticationFilter.class);
    }

    public static HttpConfigurer httpConfigurer() {
        return new HttpConfigurer();
    }
}
