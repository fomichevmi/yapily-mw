package com.mif.interview.yapily;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;

import com.mif.interview.yapily.handler.YapilyErrorHandler;

class YapilyErrorHandlerTest {

  private final YapilyErrorHandler handler = new YapilyErrorHandler();

  @Test
  void handleError_shouldThrowYapilyIntegrationException() throws IOException {
    String errorJson = "{\"error\": {\"message\": \"Invalid IBAN\"}}";
    ClientHttpResponse response = mock(ClientHttpResponse.class);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    when(response.getHeaders()).thenReturn(headers);

    when(response.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
    when(response.getBody()).thenReturn(new ByteArrayInputStream(errorJson.getBytes()));

    assertThrows(HttpClientErrorException.class, () -> handler.handleError(response));
  }
}