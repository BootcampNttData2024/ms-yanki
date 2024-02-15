package com.vasquez.msyanki.kafka.producer;

import com.vasquez.msyanki.kafka.event.KafkaEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.UUID;

@Log4j2
@Component
public class KafkaProducer<T> {

  private final KafkaTemplate<String, KafkaEvent<?>> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, KafkaEvent<?>> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publish(String topic, String type, T data) {
    KafkaEvent<T> event = new KafkaEvent<>();
    event.setId(UUID.randomUUID().toString());
    event.setDate(new Date());
    event.setEventType(type);
    event.setData(data);
    log.info("Publishing event, {}", event);
    this.kafkaTemplate.send(topic, event);
  }

}
