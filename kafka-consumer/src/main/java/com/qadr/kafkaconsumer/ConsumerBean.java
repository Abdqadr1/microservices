package com.qadr.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerBean {
    @KafkaListener(topics = "qadr")
    public void processMessage(String content) {
        System.out.println("Listener says: " + content);
    }
}
