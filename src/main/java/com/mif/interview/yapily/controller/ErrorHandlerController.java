package com.mif.interview.yapily.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mif.interview.yapily.exception.DuplicateTransactoinException;
import com.mif.interview.yapily.exception.TooManyRequestsException;

@RestControllerAdvice
public class ErrorHandlerController {

  private static final Logger logger = LogManager.getLogger(ErrorHandlerController.class);

  @ExceptionHandler(TooManyRequestsException.class)
  public ResponseEntity<TooManyRequestsException> handleTooManyRequests(TooManyRequestsException ex) {
    logger.warn("Rate limit exceeded: {}", ex.getMessage());

    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ex);
  }

  @ExceptionHandler(DuplicateTransactoinException.class)
  public ResponseEntity<DuplicateTransactoinException> handleYapilyError(DuplicateTransactoinException ex) {
    logger.error("Duplicate transactionException: {}", ex.getMessage());
    // Return the actual error received from Yapily back to the client
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
  }

  // Other business exceptions

  // Catch-all for unexpected internal errors
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneralException(Exception ex) {
    logger.error("Unexpected error occurred", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("{\"message\": \"An internal error occurred. Please contact support.\"");
  }
}
