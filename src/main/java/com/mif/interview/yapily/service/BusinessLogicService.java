package com.mif.interview.yapily.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class BusinessLogicService {

  @Autowired
  TransactionStorage transactionStorage;
  @Autowired
  InstitudionStorage institudionStorage;

  @Autowired
  YapilyHttpService yapilyHttpService;

  public void processPayment(Transaction transaction) {

    transactionStorage.updateTransactionStatus(transaction.getTransactionId(), TransactionStatus.IN_PROCESSING);

    try {
      // Fake payee for checking the functionality
      var payee = new YapilyPayee();
      payee.setName("Unicaja");
      payee.setAccountIdentifications(
          Collections.singleton(new YapilyAccountIdentification("IBAN", "ES3830049683371493489421")));

      var paymentRequest = new YapilyCreatePaymentRequest();
      paymentRequest.setPaymentIdempotencyId(transaction.getIdempotencyId());
      // Add into transaction
      paymentRequest.setType("DOMESTIC_PAYMENT");
      paymentRequest.setPayee(payee);

      var paymentAuthorizationRequest = new YapilyCreatePaymentAuthorisationRequest();
      paymentAuthorizationRequest.setInstitutionId(institudionStorage.getInstitutionForTransaction(transaction));
      paymentAuthorizationRequest.setPaymentRequest(paymentRequest);

      var consentToken = yapilyHttpService.getPaymentAuthorization(paymentAuthorizationRequest).getData()
          .getConsentToken();

      var createPaymentRequest = new YapilyCreatePaymentRequest();
      createPaymentRequest.setPaymentIdempotencyId(transaction.getIdempotencyId());
      createPaymentRequest.setType("DOMESTIC_PAYMENT");
      createPaymentRequest.setPayee(payee);
      createPaymentRequest.setAmount(new YapilyAmount(1.0, "EUR"));

      yapilyHttpService.processPayment(consentToken, createPaymentRequest);

      transactionStorage.updateTransactionStatus(transaction.getTransactionId(), TransactionStatus.PROCESSED);
    } catch (Throwable t) {
      transactionStorage.updateTransactionStatus(transaction.getTransactionId(), TransactionStatus.REJECTED);
    }
  }

}
