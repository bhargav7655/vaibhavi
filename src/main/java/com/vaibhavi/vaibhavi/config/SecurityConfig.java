package com.vaibhavi.vaibhavi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // disable CSRF for Postman
            .csrf(csrf -> csrf.disable())

            // disable session management and default login form
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/users/**").permitAll() // allow login & register
                .anyRequest().authenticated()
            )

            // disable form login (prevents 403)
            .formLogin(form -> form.disable())

            // disable http basic login
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}