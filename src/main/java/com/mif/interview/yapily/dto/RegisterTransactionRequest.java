package com.mif.interview.yapily.dto;

import java.util.Objects;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class RegisterTransactionRequest {
  private UUID empoyeeId;
  private Integer paymentDestination;
  private double amount;

  public RegisterTransactionRequest() {
  }

  public RegisterTransactionRequest(@NotNull UUID empoyeeId,
      @NotNull Integer paymentDestination, double amount) {
    this.empoyeeId = empoyeeId;
    this.paymentDestination = paymentDestination;
    this.amount = amount;
  }

  public UUID getEmpoyeeId() {
    return empoyeeId;
  }

  public void setEmpoyeeId(@NotNull UUID empoyeeId) {
    this.empoyeeId = empoyeeId;
  }

  public Integer getPaymentDestination() {
    return paymentDestination;
  }

  public void setPaymentDestination(@NotNull Integer paymentDestination) {
    this.paymentDestination = paymentDestination;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, empoyeeId, paymentDestination);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RegisterTransactionRequest other = (RegisterTransactionRequest) obj;
    return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
        && Objects.equals(empoyeeId, other.empoyeeId) && Objects.equals(paymentDestination, other.paymentDestination);
  }

  
}
