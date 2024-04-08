package com.orderizer.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.web.cors.CorsConfiguration.ALL;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain defaultSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        http.cors(corsSpec -> corsSpec.configurationSource(request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setAllowedMethods(List.of(ALL));
            corsConfiguration.setAllowedOriginPatterns(List.of(ALL));
            corsConfiguration.setAllowedHeaders(List.of(ALL));
            corsConfiguration.setMaxAge(3600L);
            return corsConfiguration;
        }));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
