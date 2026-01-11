package com.mif.interview.yapily.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.mif.interview.yapily.convertor.TransactionConverter;
import com.mif.interview.yapily.dto.*;
import com.mif.interview.yapily.service.TransactionQueueManager;
import com.mif.interview.yapily.storage.TransactionStorage;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

  private static final Logger logger = LogManager.getLogger(TransactionController.class);

  @Autowired
  TransactionQueueManager queueManager;
  @Autowired
  TransactionStorage transactionStorage;

  @PostMapping("/register")
  public ResponseEntity<RegisterTransactionResponse> register(@RequestHeader("X-Idempotency-Key") String idempotencyKey,
      @Valid @RequestBody RegisterTransactionRequest request) {
    logger.debug("Received new request: {}", request);
    var transaction = TransactionConverter.toNewTransaction(idempotencyKey, request);
    logger.debug("Created new transaction: {}", transaction);
    transactionStorage.createTransaction(transaction);
    queueManager.sendPayment(idempotencyKey, transaction);
    return new ResponseEntity<>(new RegisterTransactionResponse(), HttpStatus.OK);
  }

  @PostMapping("/details")
  public ResponseEntity<TransactionDetailsResponse> details(@Valid @RequestBody TransactionDetailsRequest request) {
    return new ResponseEntity<>(new TransactionDetailsResponse(), HttpStatus.OK);
  }
}
