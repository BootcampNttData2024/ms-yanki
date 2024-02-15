package com.vasquez.msyanki.kafka.config;

import com.vasquez.msyanki.kafka.event.KafkaEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import java.util.HashMap;
import java.util.Map;

/**
 * Kafka consumer configuration
 *
 * @author Vasquez
 * @version 1.0
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String host;

  @Bean
  public ConsumerFactory<String, KafkaEvent<?>> consumerFactory() {
    Map<String, Object> config = new HashMap<>();
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
      new JsonDeserializer<>(KafkaEvent.class, false));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, KafkaEvent<?>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, KafkaEvent<?>> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
    containerFactory.setConsumerFactory(consumerFactory());
    return containerFactory;
  }

}
