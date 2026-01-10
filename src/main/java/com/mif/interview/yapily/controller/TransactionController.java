package com.mif.interview.yapily.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.mif.interview.yapily.dto.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

  @PostMapping("/register")
  public ResponseEntity<RegisterTransactionResponse> register(
      @RequestHeader("X-Idempotency-Key") String idempotencyKey,
      @Valid @RequestBody RegisterTransactionRequest request) {
    return new ResponseEntity<>(new RegisterTransactionResponse(), HttpStatus.OK);
  }

  @PostMapping("/details")
  public ResponseEntity<TransactionDetailsResponse> details(
      @Valid @RequestBody TransactionDetailsRequest request) {
    return new ResponseEntity<>(new TransactionDetailsResponse(), HttpStatus.OK);
  }
}
