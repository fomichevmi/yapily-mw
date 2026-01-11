package com.mif.interview.yapily.dto;

/**
 * It has too many properties. I'll omit almost all of them due to lack of time
 * keeping here only essential once for checking if the app works properly
 */
public class YapilyApiError {

  private Integer code;
  private String status;
  private String message;

  public YapilyApiError() {
  }

  public YapilyApiError(Integer code, String status, String message) {
    super();
    this.code = code;
    this.status = status;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyApiError [");
    if (code != null) {
      builder.append("code=");
      builder.append(code);
      builder.append(", ");
    }
    if (status != null) {
      builder.append("status=");
      builder.append(status);
      builder.append(", ");
    }
    if (message != null) {
      builder.append("message=");
      builder.append(message);
    }
    builder.append("]");
    return builder.toString();
  }

}
