package com.vasquez.msyanki.kafka.consumer;

import com.vasquez.msyanki.business.YankiService;
import com.vasquez.msyanki.entity.Yanki;
import com.vasquez.msyanki.kafka.event.KafkaEvent;
import com.vasquez.msyanki.kafka.producer.KafkaProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


import java.util.Map;

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

  private final KafkaProducer<Yanki> KafkaProducer;
  private final YankiService yankiService;

  public KafkaConsumer(com.vasquez.msyanki.kafka.producer.KafkaProducer<Yanki> kafkaProducer, YankiService yankiService) {
    KafkaProducer = kafkaProducer;
    this.yankiService = yankiService;
  }

  @KafkaListener(topics = "P2P_TOPIC_REQ", containerFactory = "kafkaListenerContainerFactory", groupId = "BOOTCOIN_GROUP")
  public void peerToPeerConsumer(KafkaEvent<Map<String, Object>> event) {
    log.info("Listen P2P_TOPIC_REQ, {}", event.getData());
    String phoneNumber = event.getData().get("phoneNumber").toString();
    yankiService.findByPhoneNumber(phoneNumber)
      .subscribe(response -> {
        log.info("response findByPhoneNumber, {}", response);
        if (response != null)
          KafkaProducer.publish("P2P_TOPIC_RES", "CREATED", response);
      });

  }

  @KafkaListener(topics = "PURCHASE_TOPIC_REQ", containerFactory = "kafkaListenerContainerFactory", groupId = "BOOTCOIN_GROUP")
  public void purchaseConsumer(KafkaEvent<Map<String, Object>> event) {
    log.info("Listen PURCHASE_TOPIC_REQ, {}", event.getData());
  }

}
