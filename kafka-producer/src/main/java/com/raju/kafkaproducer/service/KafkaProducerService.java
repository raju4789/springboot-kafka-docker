package com.raju.kafkaproducer.service;

public interface KafkaProducerService {
    void sendMessage(String key,byte[] message);
}
