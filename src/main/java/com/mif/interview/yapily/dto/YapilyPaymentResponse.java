package com.mif.interview.yapily.dto;

/**
 * It has too many properties. I'll omit almost all of them due to lack of time
 * keeping here only essential once for checking if the app works properly
 */
public class YapilyPaymentResponse {

  /**
   * Unique identifier of the payment.
   */
  private String id;
  /**
   * Identification of the consent at the Institution.
   */
  private String institutionConsentId;
  /**
   * Mandatory. A unique identifier that you must provide to identify the payment.
   * This can be any alpha-numeric string but is limited to a maximum of 35
   * characters.
   */
  private String paymentIdempotencyId;
  /**
   * (PaymentStatus)
   * 
   * The status of the Payment.
   * 
   * For more information, see Payment Status Enum: "PENDING" "FAILED" "DECLINED"
   * "COMPLETED" "COMPLETED_SETTLEMENT_IN_PROCESS" "EXPIRED" "UNKNOWN" "ACTIVE"
   * "INACTIVE"
   */
  private String status;

  public YapilyPaymentResponse() {
  }

  public YapilyPaymentResponse(String id, String institutionConsentId, String paymentIdempotencyId, String status) {
    super();
    this.id = id;
    this.institutionConsentId = institutionConsentId;
    this.paymentIdempotencyId = paymentIdempotencyId;
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getInstitutionConsentId() {
    return institutionConsentId;
  }

  public void setInstitutionConsentId(String institutionConsentId) {
    this.institutionConsentId = institutionConsentId;
  }

  public String getPaymentIdempotencyId() {
    return paymentIdempotencyId;
  }

  public void setPaymentIdempotencyId(String paymentIdempotencyId) {
    this.paymentIdempotencyId = paymentIdempotencyId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyPaymentResponse [");
    if (id != null) {
      builder.append("id=");
      builder.append(id);
      builder.append(", ");
    }
    if (institutionConsentId != null) {
      builder.append("institutionConsentId=");
      builder.append(institutionConsentId);
      builder.append(", ");
    }
    if (paymentIdempotencyId != null) {
      builder.append("paymentIdempotencyId=");
      builder.append(paymentIdempotencyId);
      builder.append(", ");
    }
    if (status != null) {
      builder.append("status=");
      builder.append(status);
    }
    builder.append("]");
    return builder.toString();
  }

}
