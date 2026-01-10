package com.mif.interview.yapily.dto;

import java.util.UUID;

import com.mif.interview.yapily.model.TransactionStatus;

public class TransactionDetailsResponse {
  private UUID empoyeeId;
  private Integer paymentDestination;
  private double amount;
  private TransactionStatus status;

  public TransactionDetailsResponse() {
  }

  public TransactionDetailsResponse(UUID empoyeeId, Integer paymentDestination, double amount,
      TransactionStatus status) {
    super();
    this.empoyeeId = empoyeeId;
    this.paymentDestination = paymentDestination;
    this.amount = amount;
    this.status = status;
  }

  public UUID getEmpoyeeId() {
    return empoyeeId;
  }

  public void setEmpoyeeId(UUID empoyeeId) {
    this.empoyeeId = empoyeeId;
  }

  public Integer getPaymentDestination() {
    return paymentDestination;
  }

  public void setPaymentDestination(Integer paymentDestination) {
    this.paymentDestination = paymentDestination;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public TransactionStatus getStatus() {
    return status;
  }

  public void setStatus(TransactionStatus status) {
    this.status = status;
  }

}
