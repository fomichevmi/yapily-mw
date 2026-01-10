package com.mif.interview.yapily.exception;

public class UnknownMerchantException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private Integer merchantId;

  public UnknownMerchantException(Integer merchantId) {
    this.merchantId = merchantId;
  }

  @Override
  public String getMessage() {
    return "No merchant with " + merchantId + " identifier has been found!";
  }
}
