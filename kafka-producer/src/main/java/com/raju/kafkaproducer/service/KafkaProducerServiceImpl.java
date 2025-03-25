package com.raju.kafkaproducer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static final String TOPIC = "my-topic";

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendMessage(String key, byte[] message) {
        try
        {
            kafkaTemplate.send(TOPIC, key, message);
            log.info("Message sent to kafka topic:{} with key: {} ", TOPIC, key);
        }
        catch (Exception e){
            log.error("Exception in sending message to kafka topic: "+e);
        }

    }
}
