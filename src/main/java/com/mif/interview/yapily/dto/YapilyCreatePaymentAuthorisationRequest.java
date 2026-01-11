package com.mif.interview.yapily.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class YapilyCreatePaymentAuthorisationRequest {

  @NotBlank
  private String applicationUserId;
  /**
   * The reference to the Institution which identifies which institution the
   * authorisation request is sent to.
   */
  @NotBlank
  private String institutionId;
  /**
   * The payment request object defining the details of the payment.
   */
  @NotNull
  private YapilyCreatePaymentRequest paymentRequest;

  public YapilyCreatePaymentAuthorisationRequest() {
  }

  public YapilyCreatePaymentAuthorisationRequest(@NotBlank String applicationUserId, @NotBlank String institutionId,
      @NotNull YapilyCreatePaymentRequest paymentRequest) {
    this.applicationUserId = applicationUserId;
    this.institutionId = institutionId;
    this.paymentRequest = paymentRequest;
  }

  public String getInstitutionId() {
    return institutionId;
  }

  public void setInstitutionId(String institutionId) {
    this.institutionId = institutionId;
  }

  public YapilyCreatePaymentRequest getPaymentRequest() {
    return paymentRequest;
  }

  public void setPaymentRequest(YapilyCreatePaymentRequest paymentRequest) {
    this.paymentRequest = paymentRequest;
  }

  public String getApplicationUserId() {
    return applicationUserId;
  }

  public void setApplicationUserId(String applicationUserId) {
    this.applicationUserId = applicationUserId;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyCreatePaymentAuthorisationRequest [");
    if (applicationUserId != null) {
      builder.append("applicationUserId=");
      builder.append(applicationUserId);
      builder.append(", ");
    }
    if (institutionId != null) {
      builder.append("institutionId=");
      builder.append(institutionId);
      builder.append(", ");
    }
    if (paymentRequest != null) {
      builder.append("paymentRequest=");
      builder.append(paymentRequest);
    }
    builder.append("]");
    return builder.toString();
  }

}
