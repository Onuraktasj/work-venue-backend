package com.workvenue.backend.configuration;

import com.workvenue.backend.core.util.security.CustomAccessDeniedHandler;
import com.workvenue.backend.core.util.security.CustomAuthenticationEntryPoint;
import com.workvenue.backend.security.JwtAuthorizationFilter;
import com.workvenue.backend.service.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Do not format this class.
 */

@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors().and().csrf().disable().authorizeHttpRequests()
                           .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/login").permitAll()
                           .and().authorizeHttpRequests().antMatchers("/visitors/**", "/venues/**").authenticated()
                           .and().exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                           .authenticationEntryPoint(new CustomAuthenticationEntryPoint()).and().sessionManagement()
                           .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                           .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                           .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
