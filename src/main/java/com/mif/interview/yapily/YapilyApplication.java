package com.mif.interview.yapily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class YapilyApplication implements WebMvcConfigurer, ApplicationContextAware {

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
              .anyRequest().permitAll() //authenticated()
          )
          .httpBasic(Customizer.withDefaults());

      return http.build();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
  }
}
