package com.mif.interview.yapily.handler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mif.interview.yapily.dto.YapilyErrorResponse;
import com.mif.interview.yapily.exception.YapilyIntegrationException;

public class YapilyErrorHandler extends DefaultResponseErrorHandler {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
      // Read the body once
      byte[] body = getResponseBody(response);
      
      try {
          YapilyErrorResponse error = objectMapper.readValue(body, YapilyErrorResponse.class);
          // Throw a custom business exception that your @Recover method can catch
          throw new YapilyIntegrationException(error.getError().getMessage(), response.getStatusCode());
      } catch (Exception e) {
          super.handleError(response); // Fallback to default Spring behavior
      }
  }
}