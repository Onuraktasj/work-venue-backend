package com.workvenue.backend.security;

import com.workvenue.backend.core.util.security.CustomAccessDeniedHandler;
import com.workvenue.backend.core.util.security.CustomAuthenticationEntryPoint;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfigurer extends AbstractHttpConfigurer<SecurityConfigurer, HttpSecurity> {

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.cors().and().csrf().disable().authorizeHttpRequests((auth) -> {
            try {
                auth.antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/webjars/**", "/api/**")
                        .permitAll()
                        .and()
                        .exceptionHandling()
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        .and()
                        .sessionManagement()
                        .and()
                        .httpBasic();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(HttpSecurity builder) {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        builder.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public static SecurityConfigurer httpConfigurer() {
        return new SecurityConfigurer();
    }
}
