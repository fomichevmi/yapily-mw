package com.mif.interview.yapily.service;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mif.interview.yapily.dto.YapilyAccountIdentification;
import com.mif.interview.yapily.dto.YapilyAmount;
import com.mif.interview.yapily.dto.YapilyCreatePaymentAuthorisationRequest;
import com.mif.interview.yapily.dto.YapilyCreatePaymentRequest;
import com.mif.interview.yapily.dto.YapilyPayee;
import com.mif.interview.yapily.model.Transaction;
import com.mif.interview.yapily.model.TransactionStatus;
import com.mif.interview.yapily.storage.InstitudionStorage;
import com.mif.interview.yapily.storage.TransactionStorage;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Service
public class BusinessLogicService {

  private static final Logger logger = LogManager.getLogger(BusinessLogicService.class);
  private final BlockingQueue<Boolean> yapilyRequestLimiter = new ArrayBlockingQueue<>(50);

  @Autowired
  TransactionStorage transactionStorage;
  @Autowired
  InstitudionStorage institudionStorage;

  @Autowired
  YapilyHttpService yapilyHttpService;

  @PostConstruct
  public void init() {
    for (int i = 0; i < 50; i++) {
      yapilyRequestLimiter.offer(true);
    }
  }

  public void processPayment(@Valid Transaction transaction) {

    transactionStorage.updateTransactionStatus(transaction.getTransactionId(), TransactionStatus.IN_PROCESSING);

    try {
      // Fake payee for checking the functionality
      var payee = new YapilyPayee();
      payee.setName("Unicaja");
      payee.setAccountIdentifications(
          Collections.singleton(new YapilyAccountIdentification("IBAN", "ES3830049683371493489421")));

      var amount = new YapilyAmount(1.0, "EUR");

      var paymentRequest = new YapilyCreatePaymentRequest();
      paymentRequest.setPaymentIdempotencyId(transaction.getIdempotencyId());
      // Add into transaction
      paymentRequest.setType("DOMESTIC_PAYMENT");
      paymentRequest.setPayee(payee);
      paymentRequest.setAmount(amount);

      var paymentAuthorizationRequest = new YapilyCreatePaymentAuthorisationRequest();
      paymentAuthorizationRequest.setInstitutionId(institudionStorage.getInstitutionForTransaction(transaction));
      paymentAuthorizationRequest.setPaymentRequest(paymentRequest);
      paymentAuthorizationRequest.setApplicationUserId("Mikhail F");

      yapilyRequestLimiter.take();

      logger.debug("Prepared request for creating payment authorization: {}", paymentAuthorizationRequest);
      var response = yapilyHttpService.getPaymentAuthorization(paymentAuthorizationRequest);
      logger.debug("Received consent id");
      var consentToken = yapilyHttpService.getConsentToken(response.getData().getInstitutionConsentId()).getData()
          .getConsentToken();
      logger.debug("Received consent token");

      var createPaymentRequest = new YapilyCreatePaymentRequest();
      createPaymentRequest.setPaymentIdempotencyId(transaction.getIdempotencyId());
      createPaymentRequest.setType("DOMESTIC_PAYMENT");
      createPaymentRequest.setPayee(payee);
      createPaymentRequest.setAmount(amount);

      logger.debug("Prepared request for creating payment");
      yapilyHttpService.processPayment(consentToken, createPaymentRequest);
      logger.debug("Payment created");

      transactionStorage.updateTransactionStatus(transaction.getTransactionId(), TransactionStatus.PROCESSED);
    } catch (Throwable t) {
      transactionStorage.updateTransactionStatus(transaction.getTransactionId(), TransactionStatus.REJECTED);
      logger.error("Error accurred while performing request to Yapily: {}", t.getMessage());
      throw new RuntimeException(t);
    }
  }

  @Scheduled(fixedRate = 100) // Refill every 100ms (10 tokens/sec)
  public void refill() {
    yapilyRequestLimiter.offer(true);
  }
}
