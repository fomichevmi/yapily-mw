package com.mif.interview.yapily.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class YapilyCreatePaymentAuthorisationRequest {

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

  public YapilyCreatePaymentAuthorisationRequest(@NotBlank String institutionId,
      @NotNull YapilyCreatePaymentRequest paymentRequest) {
    super();
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

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyCreatePaymentAuthorisationRequest [");
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
