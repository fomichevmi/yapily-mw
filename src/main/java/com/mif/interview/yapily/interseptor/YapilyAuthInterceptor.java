package com.mif.interview.yapily.interseptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class YapilyAuthInterceptor implements ClientHttpRequestInterceptor {
  private final String apiKey;
  private final String apiSecret;

  public YapilyAuthInterceptor(String apiKey, String apiSecret) {
      this.apiKey = apiKey;
      this.apiSecret = apiSecret;
  }

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
      String auth = apiKey + ":" + apiSecret;
      String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
      request.getHeaders().add("Authorization", "Basic " + encodedAuth);
      return execution.execute(request, body);
  }
}
