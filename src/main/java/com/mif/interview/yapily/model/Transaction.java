package com.mif.interview.yapily.model;

import java.util.Objects;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Transaction {

  private String idempotencyId;
  private UUID transactionId;
  private UUID empoyeeId;
  private Integer paymentDestination;
  private double amount;
  private TransactionStatus status;

  public Transaction() {
  }

  public Transaction(@NotBlank String idempotencyId, @NotNull UUID transactionId, @NotNull UUID empoyeeId,
      @NotNull Integer paymentDestination, @Positive double amount, TransactionStatus status) {
    this.idempotencyId = idempotencyId;
    this.transactionId = transactionId;
    this.empoyeeId = empoyeeId;
    this.paymentDestination = paymentDestination;
    this.amount = amount;
    this.status = status;
  }

  public String getIdempotencyId() {
    return idempotencyId;
  }

  public void setIdempotencyId(@NotBlank String idempotencyId) {
    this.idempotencyId = idempotencyId;
  }

  public UUID getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(@NotNull UUID transactionId) {
    this.transactionId = transactionId;
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

  public void setAmount(@Positive double amount) {
    this.amount = amount;
  }

  public TransactionStatus getStatus() {
    return status;
  }

  public void setStatus(@NotNull TransactionStatus status) {
    this.status = status;
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
    Transaction other = (Transaction) obj;
    return Objects.equals(transactionId, other.transactionId);
  }

  @Override
  public String toString() {
    return new StringBuilder().append("Transaction [idempotencyId=").append(idempotencyId).append(", transactionId=")
        .append(transactionId).append(", empoyeeId=").append(empoyeeId).append(", paymentDestination=")
        .append(paymentDestination).append(", amount=").append(amount).append(", status=").append(status).append("]")
        .toString();
  }

}
