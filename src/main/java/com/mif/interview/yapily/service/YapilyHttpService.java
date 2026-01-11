package com.mif.interview.yapily.service;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.mif.interview.yapily.model.Transaction;

@Service
public class YapilyHttpService {

  private static final Logger logger = LogManager.getLogger(YapilyHttpService.class);
  private static final URI YAPILY_HOST = URI.create("https://api.yapily.com");
  
  @Autowired
  RestTemplate restTemplate;

  @Retryable(retryFor = { ResourceAccessException.class,
      HttpServerErrorException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 2.0))
  private void callYapily(Transaction tx) {
    logger.info("Attempting to send transaction {} to Yapily...", tx.getTransactionId());

    // This will throw ResourceAccessException on timeout
    // or HttpServerErrorException on 5xx errors, triggering the retry.
    restTemplate.postForEntity("https://api.yapily.com/payments", tx, String.class);
  }

  @Recover
  private void recover(Exception e, Transaction tx) {
    logger.error("All 3 retry attempts failed for transaction {}. Sending to DLQ.", tx.getTransactionId());
    // TODO: Logic to move message to a Dead Letter Queue (DLQ) in Kafka
  }
}
