package com.mif.interview.yapily.dto;

import java.util.Objects;

public class YapilyResponseMeta {

  private String tracingId;

  public YapilyResponseMeta() {
  }

  public YapilyResponseMeta(String tracingId) {
    super();
    this.tracingId = tracingId;
  }

  public String getTracingId() {
    return tracingId;
  }

  public void setTracingId(String tracingId) {
    this.tracingId = tracingId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(tracingId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    YapilyResponseMeta other = (YapilyResponseMeta) obj;
    return Objects.equals(tracingId, other.tracingId);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyResponseMeta [");
    if (tracingId != null) {
      builder.append("tracingId=");
      builder.append(tracingId);
    }
    builder.append("]");
    return builder.toString();
  }

}
