package com.example.demo.messaging

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class TestProducer(@Autowired private val kafkaTemplate: KafkaTemplate<String, String>) {

    fun send(message: String) {
        kafkaTemplate.send("test", message)
    }
}