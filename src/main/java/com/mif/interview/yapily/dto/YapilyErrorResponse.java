package com.mif.interview.yapily.dto;

public class YapilyErrorResponse {

  private YapilyApiError error;

  public YapilyErrorResponse() {
  }

  public YapilyErrorResponse(YapilyApiError error) {
    this.error = error;
  }

  public YapilyApiError getError() {
    return error;
  }

  public void setError(YapilyApiError error) {
    this.error = error;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyErrorResponse [");
    if (error != null) {
      builder.append("error=");
      builder.append(error);
    }
    builder.append("]");
    return builder.toString();
  }

}
