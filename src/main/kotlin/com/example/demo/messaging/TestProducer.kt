package com.example.demo.messaging

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class TestProducer(private val kafkaTemplate: KafkaTemplate<String, String>) {

    fun send(message: String) {
        kafkaTemplate.send("test", message)
    }
}