package com.mif.interview.yapily.exception;

public class TransactionQueueingException extends RuntimeException {

  private static final long serialVersionUID = 7123636481532105011L;

  public TransactionQueueingException(Throwable cause) {
    super("An error occurred while queueing transaction", cause);
  }

}
