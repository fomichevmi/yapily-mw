package com.mif.interview.yapily.exception;

import org.springframework.http.HttpStatusCode;

public class YapilyIntegrationException extends RuntimeException {

  private static final long serialVersionUID = 2872898226456434422L;

  private String message;
  private HttpStatusCode statusCode;

  public YapilyIntegrationException(String message, HttpStatusCode statusCode) {
    this.message = message;
    this.statusCode = statusCode;
  }

  @Override
  public String toString() {
    return new StringBuilder().append("Yapily API call failed with code ")
        .append(statusCode.value()).append(" with reason: ").append(message).toString();
  }
}
