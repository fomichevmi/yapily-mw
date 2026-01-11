package com.mif.interview.yapily.exception;

import com.mif.interview.yapily.model.TransactionStatus;

public class DuplicateTransactoinException extends RuntimeException {

  private static final long serialVersionUID = -6343196183736310287L;

  public TransactionStatus status;

  public DuplicateTransactoinException(TransactionStatus status) {
    this.status = status;
  }

  @Override
  public String getMessage() {
    return new StringBuilder().append("The transaction is already ").append(status.name().replaceAll("_", ""))
        .toString();
  }
}
