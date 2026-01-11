package com.mif.interview.yapily.dto;

public class YapilyPaymentAuthorisationResponse {

  private String id;
  private String userUuid;
  private String status;
  private String createdAt;
  private String transactionFrom;
  private String transactionTo;
  private String expiresAt;
  private String timeToExpire;
  private String fetureScope;
  private String institutionConsentId;
  private String state;
  private String explanation;

  public YapilyPaymentAuthorisationResponse() {
  }

  public YapilyPaymentAuthorisationResponse(String id, String userUuid, String status, String createdAt,
      String transactionFrom, String transactionTo, String expiresAt, String timeToExpire, String fetureScope,
      String institutionConsentId, String state, String explanation) {
    this.id = id;
    this.userUuid = userUuid;
    this.status = status;
    this.createdAt = createdAt;
    this.transactionFrom = transactionFrom;
    this.transactionTo = transactionTo;
    this.expiresAt = expiresAt;
    this.timeToExpire = timeToExpire;
    this.fetureScope = fetureScope;
    this.institutionConsentId = institutionConsentId;
    this.state = state;
    this.explanation = explanation;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(String userUuid) {
    this.userUuid = userUuid;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getTransactionFrom() {
    return transactionFrom;
  }

  public void setTransactionFrom(String transactionFrom) {
    this.transactionFrom = transactionFrom;
  }

  public String getTransactionTo() {
    return transactionTo;
  }

  public void setTransactionTo(String transactionTo) {
    this.transactionTo = transactionTo;
  }

  public String getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(String expiresAt) {
    this.expiresAt = expiresAt;
  }

  public String getTimeToExpire() {
    return timeToExpire;
  }

  public void setTimeToExpire(String timeToExpire) {
    this.timeToExpire = timeToExpire;
  }

  public String getFetureScope() {
    return fetureScope;
  }

  public void setFetureScope(String fetureScope) {
    this.fetureScope = fetureScope;
  }

  public String getInstitutionConsentId() {
    return institutionConsentId;
  }

  public void setInstitutionConsentId(String institutionConsentId) {
    this.institutionConsentId = institutionConsentId;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getExplanation() {
    return explanation;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyPaymentAuthorisationRequestResponse [");
    if (id != null) {
      builder.append("id=");
      builder.append(id);
      builder.append(", ");
    }
    if (userUuid != null) {
      builder.append("userUuid=");
      builder.append(userUuid);
      builder.append(", ");
    }
    if (status != null) {
      builder.append("status=");
      builder.append(status);
      builder.append(", ");
    }
    if (createdAt != null) {
      builder.append("createdAt=");
      builder.append(createdAt);
      builder.append(", ");
    }
    if (transactionFrom != null) {
      builder.append("transactionFrom=");
      builder.append(transactionFrom);
      builder.append(", ");
    }
    if (transactionTo != null) {
      builder.append("transactionTo=");
      builder.append(transactionTo);
      builder.append(", ");
    }
    if (expiresAt != null) {
      builder.append("expiresAt=");
      builder.append(expiresAt);
      builder.append(", ");
    }
    if (timeToExpire != null) {
      builder.append("timeToExpire=");
      builder.append(timeToExpire);
      builder.append(", ");
    }
    if (fetureScope != null) {
      builder.append("fetureScope=");
      builder.append(fetureScope);
      builder.append(", ");
    }
    if (institutionConsentId != null) {
      builder.append("institutionConsentId=");
      builder.append(institutionConsentId);
      builder.append(", ");
    }
    if (state != null) {
      builder.append("state=");
      builder.append(state);
      builder.append(", ");
    }
    if (explanation != null) {
      builder.append("explanation=");
      builder.append(explanation);
    }
    builder.append("]");
    return builder.toString();
  }

}
