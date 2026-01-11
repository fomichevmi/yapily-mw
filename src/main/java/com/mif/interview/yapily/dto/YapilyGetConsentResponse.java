package com.mif.interview.yapily.dto;

public class YapilyGetConsentResponse {

  private YapilyResponseMeta meta;
  private YapilyConsent data;

  public YapilyGetConsentResponse() {

  }

  public YapilyGetConsentResponse(YapilyResponseMeta meta, YapilyConsent data) {
    super();
    this.meta = meta;
    this.data = data;
  }

  public YapilyResponseMeta getMeta() {
    return meta;
  }

  public void setMeta(YapilyResponseMeta meta) {
    this.meta = meta;
  }

  public YapilyConsent getData() {
    return data;
  }

  public void setData(YapilyConsent data) {
    this.data = data;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyGetConsentResponse [");
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
