package com.mif.interview.yapily.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mif.interview.yapily.exception.TooManyRequestsException;
import com.mif.interview.yapily.exception.TransactionQueueingException;
import com.mif.interview.yapily.model.Transaction;
import com.mif.interview.yapily.model.TransactionStatus;
import com.mif.interview.yapily.storage.TransactionStorage;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Service
public class TransactionQueueManager {

  private static final Logger logger = LogManager.getLogger(TransactionQueueManager.class);
  private final BlockingQueue<Boolean> ddosProtection = new ArrayBlockingQueue<>(500);

  @Autowired
  KafkaTemplate<String, Transaction> kafkaTemplate;

  @Autowired
  TransactionStorage transactionStorage;

  @Autowired
  BusinessLogicService businessLogicService;

  @PostConstruct
  public void init() {
    for (int i = 0; i < 500; i++) {
      ddosProtection.offer(true);
    }
  }

  public void sendPayment(@NotBlank String idempotencyKey, @Valid Transaction request) {
    if (ddosProtection.poll() != null) {
      // We use the idempotencyKey as the Kafka Message Key.
      // This ensures all retries for the same payment go to the same partition.
      logger.debug("Sending transaction {} to the queue", request);
      kafkaTemplate.send("payments-topic", idempotencyKey, request).whenComplete((result, ex) -> {
        if (ex == null) {
          transactionStorage.updateTransactionStatus(request.getTransactionId(), TransactionStatus.IN_QUEUE);
          logger.debug("Sent message=[{}] with offset=[{}]", request, result.getRecordMetadata().offset());
        } else {
          transactionStorage.updateTransactionStatus(request.getTransactionId(), TransactionStatus.REJECTED);
          throw new TransactionQueueingException(ex);
        }
      });
    } else {
      throw new TooManyRequestsException();
    }
  }

  @KafkaListener(topics = "payments-topic", groupId = "payment-middleware-group")
  public void consume(Transaction transaction) {
    logger.debug("Processing payment for key: {}, amount: {}", transaction.getTransactionId(), transaction.getAmount());
    try {
      businessLogicService.processPayment(transaction);
    } catch (Exception e) {
      // Reject transaction
    }
  }

  @Scheduled(fixedRate = 10) // Refill every 10ms (100 tokens/sec)
  public void refill() {
    ddosProtection.offer(true);
  }

}
