package com.workvenue.backend.security;

import com.workvenue.backend.core.util.exception.CustomAccessDeniedHandler;
import com.workvenue.backend.core.util.exception.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfigurer extends AbstractHttpConfigurer<SecurityConfigurer, HttpSecurity> {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.cors().and().csrf().disable().authorizeHttpRequests((auth) -> {
            try {
                auth.antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/webjars/**", "/api/**")
                    .permitAll()
                    .antMatchers("/visitors").hasAnyAuthority("ROLE_ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
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
        builder.addFilter(new JwtAuthenticationFilter(authenticationManager))
               .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public static SecurityConfigurer httpConfigurer() {
        return new SecurityConfigurer();
    }
}
