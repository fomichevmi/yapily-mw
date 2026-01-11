package com.mif.interview.yapily.exception;

public class TooManyRequestsException extends RuntimeException {

  private static final long serialVersionUID = 9173571289327220642L;

  public TooManyRequestsException() {
  }

  @Override
  public String getMessage() {
    return "Exceeded maximum request rate, please, try again later";
  }
}
