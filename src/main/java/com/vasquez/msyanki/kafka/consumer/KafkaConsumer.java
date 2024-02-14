package com.vasquez.msyanki.kafka.consumer;

import com.vasquez.msyanki.kafka.event.KafkaEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Kafka consumer
 *
 * @author Vasquez
 * @version 1.0
 */
@Log4j2
@EnableKafka
@Component
public class KafkaConsumer {

  @KafkaListener(topics = "TRANSACTION_YANKI", containerFactory = "kafkaListenerContainerFactory", groupId = "group-yank")
  public void kafKaConsumer(KafkaEvent<Map<String, Object>> event) {
    log.info("Kafka consumer, {}", event.getData());
  }

}
