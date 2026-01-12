package com.mif.interview.yapily;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ProducerFactory;

import com.mif.interview.yapily.model.Transaction;

@SpringBootTest(classes = KafkaProducerConfig.class)
class KafkaProducerConfigTest {

  @Autowired
  private ProducerFactory<String, Transaction> producerFactory;

  @Test
  void producerConfig_shouldHaveIdempotenceEnabled() {
    Map<String, Object> configs = producerFactory.getConfigurationProperties();
    assertEquals(true, configs.get(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG));
    assertEquals("all", configs.get(ProducerConfig.ACKS_CONFIG));
  }
}