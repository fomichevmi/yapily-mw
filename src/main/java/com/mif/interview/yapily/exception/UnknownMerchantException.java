package com.mif.interview.yapily.exception;

public class UnknownMerchantException extends RuntimeException {

  private static final long serialVersionUID = -3481627069355500943L;
  private Integer merchantId;

  public UnknownMerchantException(Integer merchantId) {
    this.merchantId = merchantId;
  }

  @Override
  public String getMessage() {
    return new StringBuilder().append("No merchant with ").append(merchantId).append(" identifier has been found!")
        .toString();
  }
}
