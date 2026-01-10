package com.mif.interview.yapily.dto;

import java.util.Objects;

public class RegisterTransactionResponse {

  private String transactionId;

  public RegisterTransactionResponse() {
  }

  public RegisterTransactionResponse(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RegisterTransactionResponse other = (RegisterTransactionResponse) obj;
    return Objects.equals(transactionId, other.transactionId);
  }

}
