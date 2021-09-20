package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
