package com.microservice.authService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        SecurityWebFilterChain build = http.csrf().disable().cors().and().authorizeExchange().pathMatchers("/**").permitAll().and().build();
        return build;
    }
}
