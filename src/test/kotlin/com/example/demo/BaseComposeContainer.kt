package com.example.demo

import org.junit.ClassRule
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.DockerImageName

open class BaseComposeContainer {
    companion object {
        @ClassRule
        var kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"))
            .withExposedPorts(9093, 8080)
            .waitingFor(Wait.forHttp("/"))

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            kafka.start()
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            kafka.close()
        }
    }
}
