package com.mif.interview.yapily;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.mif.interview.yapily.model.Transaction;

@Configuration
public class KafkaProducerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  NewTopic paymentsTopic() {
    return TopicBuilder.name("payments-topic").partitions(3).replicas(1).build();
  }

  @Bean
  ProducerFactory<String, Transaction> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

    // Fintech Safety: Idempotence and ACKS
    configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
    configProps.put(ProducerConfig.ACKS_CONFIG, "all");

    // Use the constructor approach to avoid InstantiationException
    return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), new JsonSerializer<>());
  }

  @Bean
  KafkaTemplate<String, Transaction> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
