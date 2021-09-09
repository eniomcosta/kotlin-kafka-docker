package com.example.demo.controller

import com.example.demo.messaging.TestProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MessageApi(private val kotlinProducer: TestProducer) {

    @PostMapping("/message")
    fun publish(@RequestBody message: String) {
        kotlinProducer.send(message)
    }

}