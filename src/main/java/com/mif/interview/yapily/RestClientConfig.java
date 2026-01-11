package com.mif.interview.yapily;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mif.interview.yapily.handler.YapilyErrorHandler;
import com.mif.interview.yapily.interseptor.YapilyAuthInterceptor;

@Configuration
public class RestClientConfig {

  @Value("${apiKey}")
  private String yapilyKey;

  @Value("${apiSecret}")
  private String yapilySecret;

  @Bean
  RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.setConnectTimeout(Duration.ofSeconds(2)).setReadTimeout(Duration.ofSeconds(5))
        .errorHandler(new YapilyErrorHandler())
        .additionalInterceptors(new YapilyAuthInterceptor(yapilyKey, yapilySecret)).build();
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(new JavaTimeModule());
  }
}
