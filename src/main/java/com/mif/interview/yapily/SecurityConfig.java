package com.mif.interview.yapily;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disabled for local development/testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // Only admins can see management/actuator endpoints if you have them
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Users and Admins can create payments
                .requestMatchers("/api/v1/payments/**").hasAnyRole("USER", "ADMIN")
                // Everything else requires authentication
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Use Basic Auth for easy Postman testing

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
