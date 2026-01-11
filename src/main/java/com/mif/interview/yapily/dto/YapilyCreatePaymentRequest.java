package com.mif.interview.yapily.dto;

import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class YapilyCreatePaymentRequest {

  @NotBlank
  private String paymentIdempotencyId;
  @Valid
  private YapilyPayer payer;
  private String reference;
  /**
   * (Payment Code) Default: "OTHER"
   * 
   * Optional. The payment context code. This defaults to OTHER if not specified.
   * Enum: "BILL" "GOODS" "SERVICES" "OTHER" "PERSON_TO_PERSON" "BILL_IN_ADVANCE"
   * "BILL_IN_ARREARS" "ECOMMERCE_MERCHANT" "FACE_TO_FACE_POS" "TRANSFER_TO_SELF"
   * "TRANSFER_TO_THIRD_PARTY" "PISP_PAYEE"
   */
  private String contextType;
  /**
   * (Payment Purpose Code)
   * 
   * Optional. The payment purpose code.
   * 
   * Allowed values: INTP, DEPT, BEXP, LICF, SERV, SUPP, TRAD, SUBS, GDSV, ROYA,
   * COMT, CHAR, ECPR, CLPR, INTE, LOAN, LOAR, INPC, INPR, INSC, INSU, LIFI, PPTI,
   * HLRP, HLST, PDEP, IVPT, REBT, REFU, CDBL, CPKC, EDUC, FEES, GAMB, LOTT, GIFT,
   * INSM, REOD, GOVT, TCSC, BLDM, RENT, DIVD, INVS, SAVG, HLTI, DNTS, LTCF, MDCS,
   * VIEW, BECH, BENE, SSBE, PEFC, PENS, ADCS, BONU, COMM, SALA, ESTX, HSTX, INTX,
   * PTXP, RDTX, TAXS, VATX, WHLD, TAXR, CBTV, ELEC, GASB, PHON, UBIL, WTER .
   * 
   * See Payment Purpose code
   * (https://docs.yapily.com/pages/payments/payments-resources/tri-pilot/) to see
   * the definition of each code
   * 
   */
  private String purposeCode;
  /**
   * (Payment Type)
   * 
   * Mandatory. Used to specify which of the payment types to execute.
   * 
   * See European Payments to verify whether the type should be DOMESTIC or
   * INTERNATIONAL. Enum: "DOMESTIC_PAYMENT" "DOMESTIC_INSTANT_PAYMENT"
   * "DOMESTIC_VARIABLE_RECURRING_PAYMENT" "DOMESTIC_SCHEDULED_PAYMENT"
   * "DOMESTIC_PERIODIC_PAYMENT" "INTERNATIONAL_PAYMENT"
   * "INTERNATIONAL_SCHEDULED_PAYMENT" "INTERNATIONAL_PERIODIC_PAYMENT"
   * "BULK_PAYMENT"
   */
  @NotBlank
  private String type;
  /**
   * (Payee Details)
   * 
   * Mandatory. Details of the beneficiary [person or business].
   * 
   */
  @NotNull
  @Valid
  private YapilyPayee payee;
  // Skipping as optional, creation takes time
  // private YapilyPeriodicPayment periodicPayment;
  // private YapilyInternationalPayment internationalPayment;
  @NotNull
  @Valid
  private YapilyAmount amount;

  public YapilyCreatePaymentRequest() {
  }

  public YapilyCreatePaymentRequest(@NotBlank String paymentIdempotencyId, @Valid YapilyPayer payer, String reference,
      String contextType, String purposeCode, @NotBlank String type, @NotNull @Valid YapilyPayee payee,
      @NotNull @Valid YapilyAmount amount) {
    super();
    this.paymentIdempotencyId = paymentIdempotencyId;
    this.payer = payer;
    this.reference = reference;
    this.contextType = contextType;
    this.purposeCode = purposeCode;
    this.type = type;
    this.payee = payee;
    this.amount = amount;
  }

  public String getPaymentIdempotencyId() {
    return paymentIdempotencyId;
  }

  public void setPaymentIdempotencyId(String paymentIdempotencyId) {
    this.paymentIdempotencyId = paymentIdempotencyId;
  }

  public YapilyPayer getPayer() {
    return payer;
  }

  public void setPayer(YapilyPayer payer) {
    this.payer = payer;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getContextType() {
    return contextType;
  }

  public void setContextType(String contextType) {
    this.contextType = contextType;
  }

  public String getPurposeCode() {
    return purposeCode;
  }

  public void setPurposeCode(String purposeCode) {
    this.purposeCode = purposeCode;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public YapilyPayee getPayee() {
    return payee;
  }

  public void setPayee(YapilyPayee payee) {
    this.payee = payee;
  }

  public YapilyAmount getAmount() {
    return amount;
  }

  public void setAmount(YapilyAmount amount) {
    this.amount = amount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentIdempotencyId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    YapilyCreatePaymentRequest other = (YapilyCreatePaymentRequest) obj;
    return Objects.equals(paymentIdempotencyId, other.paymentIdempotencyId);
  }

  
}
