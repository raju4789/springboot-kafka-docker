package com.raju.kafkaconsumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl {
    private static final String TOPIC = "my-topic";


    @KafkaListener(topics = TOPIC, groupId = "kafka-demo-group")
    public void consume(ConsumerRecord<String, byte[]> record) {
        String key = record.key();
        byte[] value = record.value();
        System.out.println("Consumed message: Key = " + key + ", Value = " + new String(value));
    }
}
