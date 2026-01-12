package com.mif.interview.yapily.storage;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mif.interview.yapily.exception.DuplicateTransactoinException;
import com.mif.interview.yapily.model.Transaction;
import com.mif.interview.yapily.model.TransactionStatus;
import com.mif.interview.yapily.service.MetricsEmmiterService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * There should be database behind this repository, the task for the future
 * Making it with maps because i don't have much time now
 */
@Repository
public class TransactionStorage {

  private static final Logger logger = LogManager.getLogger(TransactionStorage.class);

  final private ConcurrentMap<UUID, Transaction> transactionsById = new ConcurrentHashMap<>();
  final private ConcurrentMap<String, Transaction> transactionsByIdempotency = new ConcurrentHashMap<>();

  @Autowired
  MetricsEmmiterService metricsService;

  public Transaction createTransaction(@Valid Transaction transaction) {
    var transactionByIdempotency = transactionsByIdempotency.putIfAbsent(transaction.getIdempotencyId(), transaction);
    if (transactionByIdempotency != null) {
      if (transactionByIdempotency.equals(transaction)) {
        // Same transaction created twice
        return transactionByIdempotency;
      } else {
        // Same idempotencyId but different content, shouldn't happen
        metricsService.emitDuplicated();
        transaction.setStatus(TransactionStatus.DUPLICATE);
        transactionsById.putIfAbsent(transaction.getTransactionId(), transaction);
        logger.error("Idempotation {} has already been assigned to a different transaction {}, rejecting: {}",
            transaction.getIdempotencyId(), transactionByIdempotency.getTransactionId(), transaction);
        throw new DuplicateTransactoinException(transactionByIdempotency.getStatus());
      }
    } else {
      Transaction transactionById = null;
      do {
        transactionById = transactionsById.putIfAbsent(transaction.getTransactionId(), transaction);
        // Nearly impossible case but there is a chance the transaction with such uuid
        // already exists in the storage
        // But since idempotency identifier is different, we need to refresh the id
        // Another option would be to tell the client that transaction with such id
        // already exists
        // in the storage, but it would create more confusion than benefit since we
        // generate it
        if (transactionById != null) {
          transaction.setTransactionId(UUID.randomUUID());
          logger.warn("Duplicate transaction id detected: {}, trying to store new trunsaction with refreshed id: {}",
              transactionById.getTransactionId(), transaction.getTransactionId());
        } else {;
          logger.debug("Transaction with id {} has been successfully saved", transaction.getTransactionId());
        }
      } while (transactionById != null);
    }
    return transaction;
  }

  public Transaction updateTransactionStatus(@NotNull UUID transactionId, @NotNull TransactionStatus newStatus) {
    return transactionsById.computeIfPresent(transactionId, (id, t) -> {
      t.setStatus(newStatus);
      logger.debug("Status for transaction {} changed to {}", transactionId, newStatus);
      return t;
    });
  }
}
