package com.mif.interview.yapily.dto;

public class YapilyCreatePaymentResponse {

  private YapilyResponseMeta meta;
  private YapilyPaymentResponse data;

  public YapilyCreatePaymentResponse() {
  }

  public YapilyCreatePaymentResponse(YapilyResponseMeta meta, YapilyPaymentResponse data) {
    this.meta = meta;
    this.data = data;
  }

  public YapilyResponseMeta getMeta() {
    return meta;
  }

  public void setMeta(YapilyResponseMeta meta) {
    this.meta = meta;
  }

  public YapilyPaymentResponse getData() {
    return data;
  }

  public void setData(YapilyPaymentResponse data) {
    this.data = data;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyCreatePaymentResponse [");
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
