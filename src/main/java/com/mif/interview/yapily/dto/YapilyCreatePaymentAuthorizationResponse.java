package com.mif.interview.yapily.dto;

public class YapilyCreatePaymentAuthorizationResponse {

  private YapilyResponseMeta meta;
  private YapilyPaymentAuthorisationRequestResponse data;

  public YapilyCreatePaymentAuthorizationResponse() {
  }

  public YapilyCreatePaymentAuthorizationResponse(YapilyResponseMeta meta,
      YapilyPaymentAuthorisationRequestResponse data) {
    this.meta = meta;
    this.data = data;
  }

  public YapilyResponseMeta getMeta() {
    return meta;
  }

  public void setMeta(YapilyResponseMeta meta) {
    this.meta = meta;
  }

  public YapilyPaymentAuthorisationRequestResponse getData() {
    return data;
  }

  public void setData(YapilyPaymentAuthorisationRequestResponse data) {
    this.data = data;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyCreatePaymentAuthorizationResponse [");
    if (meta != null) {
      builder.append("meta=");
      builder.append(meta);
      builder.append(", ");
    }
    if (data != null) {
      builder.append("data=");
      builder.append(data);
    }
    builder.append("]");
    return builder.toString();
  }

}
