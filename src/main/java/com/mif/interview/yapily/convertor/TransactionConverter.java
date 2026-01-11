package com.mif.interview.yapily.convertor;

import java.util.UUID;

import com.mif.interview.yapily.dto.RegisterTransactionRequest;
import com.mif.interview.yapily.model.Transaction;
import com.mif.interview.yapily.model.TransactionStatus;

public class TransactionConverter {

  public static Transaction toNewTransaction(String idempotencyId, RegisterTransactionRequest request) {
    var transaction = new Transaction();
    transaction.setAmount(request.getAmount());
    transaction.setEmpoyeeId(request.getEmpoyeeId());
    transaction.setIdempotencyId(idempotencyId);
    transaction.setPaymentDestination(request.getPaymentDestination());
    transaction.setStatus(TransactionStatus.NEW);
    transaction.setTransactionId(UUID.randomUUID());
    return transaction;
  }
}