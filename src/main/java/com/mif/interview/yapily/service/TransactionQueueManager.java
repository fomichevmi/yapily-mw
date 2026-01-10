package com.mif.interview.yapily.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mif.interview.yapily.model.Transaction;

@Service
public class TransactionQueueManager {

  @Qualifier("kafkaTemplate")
  KafkaTemplate<String, Transaction> kafkaTemplate;

  public void sendPayment(String idempotencyKey, Transaction request) {
    // We use the idempotencyKey as the Kafka Message Key.
    // This ensures all retries for the same payment go to the same partition.
    kafkaTemplate.send("payments-topic", idempotencyKey, request).whenComplete((result, ex) -> {
      if (ex == null) {
        System.out.println("Sent message=[" + request + "] with offset=[" + result.getRecordMetadata().offset() + "]");
      } else {
        System.err.println("Unable to send message=[" + request + "] due to : " + ex.getMessage());
      }
    });
  }

  @KafkaListener(topics = "payments-topic")
  public void consume(ConsumerRecord<String, Transaction> record) {
      String key = record.key();
      Transaction payload = record.value();
      
      // LOGIC: Call Yapily API here.
      // If this thread blocks (waiting for Yapily), it's a Virtual Thread, 
      // so it doesn't consume heavy system resources!
      System.out.printf("Processing payment for key: %s, amount: %s%n", key, payload.getAmount());
  }
}
