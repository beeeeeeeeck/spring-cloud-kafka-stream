package com.bk.producer.service;

import com.bk.message.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class OrderService {

    private final static String KAFKA_ORDER_TOPIC = "order.push";

    private KafkaTemplate<String, Order> kafkaTemplate;

    public void pushOneOrder() {
        Order order = Order.builder()
            .id(UUID.randomUUID().toString())
            .amount(ThreadLocalRandom.current().nextLong(100))
            .price(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1000)))
            .build();

        if (order.getPrice().toBigInteger().mod(BigInteger.TWO).intValue() == 0) {
            order.setActive(Boolean.FALSE);
        }

        kafkaTemplate.send(KAFKA_ORDER_TOPIC, order);
        log.info("Push order - {} which status is {}", order.getId(), order.getActive());
    }

    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

}
