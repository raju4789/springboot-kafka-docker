package com.raju.kafkaproducer.controller;

import com.raju.kafkaproducer.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping
    public String sendMessage(@RequestParam String key, @RequestBody String message) {
        log.info("Sending message to kafka topic with key: {}", key);
        kafkaProducerService.sendMessage(key, message.getBytes());
        return "Message sent successfully!";
    }
}