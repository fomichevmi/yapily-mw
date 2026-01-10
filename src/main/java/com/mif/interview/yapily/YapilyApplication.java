package com.mif.interview.yapily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class YapilyApplication {

  public static void main(String[] args) {
    SpringApplication.run(YapilyApplication.class, args);
    System.out.println("I'm alive");
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
              .anyRequest().authenticated()
          )
          .httpBasic(Customizer.withDefaults());

      return http.build();
  }
}
