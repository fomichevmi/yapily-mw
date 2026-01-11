package com.mif.interview.yapily.dto;

public class YapilyConsent {
  
  private String instintutionId;
  private String createdAt;
  private String consentToken;
  
  public YapilyConsent() {}

  public YapilyConsent(String instintutionId, String createdAt, String consentToken) {
    super();
    this.instintutionId = instintutionId;
    this.createdAt = createdAt;
    this.consentToken = consentToken;
  }

  public String getInstintutionId() {
    return instintutionId;
  }

  public void setInstintutionId(String instintutionId) {
    this.instintutionId = instintutionId;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getConsentToken() {
    return consentToken;
  }

  public void setConsentToken(String consentToken) {
    this.consentToken = consentToken;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyConsent [");
    if (instintutionId != null) {
      builder.append("instintutionId=");
      builder.append(instintutionId);
      builder.append(", ");
    }
    if (createdAt != null) {
      builder.append("createdAt=");
      builder.append(createdAt);
      builder.append(", ");
    }
    if (consentToken != null) {
      builder.append("consentToken=");
      builder.append(consentToken != null ? "*************" : "null");
    }
    builder.append("]");
    return builder.toString();
  }
  
  
}
