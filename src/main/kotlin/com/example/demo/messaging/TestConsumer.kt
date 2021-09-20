package com.example.demo.messaging

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class TestConsumer {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["test"], groupId = "group_id")
    fun consume(message: String) {
        logger.info("message: $message")
    }
}
