package com.mif.interview.yapily;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
        .additionalInterceptors(new YapilyAuthInterceptor(yapilyKey, yapilySecret)).build();
  }
}
