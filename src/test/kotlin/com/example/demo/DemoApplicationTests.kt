package com.example.demo

import com.example.demo.messaging.TestConsumer
import com.example.demo.messaging.TestProducer
import org.junit.ClassRule
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.io.File

@Testcontainers
@SpringBootTest
class DemoApplicationTests {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var kotlinProducer: TestProducer

    companion object {
        private val instance: KDockerComposeContainer by lazy { defineDockerCompose() }

        class KDockerComposeContainer(file: File) : DockerComposeContainer<KDockerComposeContainer>(file)

        private fun defineDockerCompose() = KDockerComposeContainer(File("docker-compose.yml"))

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            instance
                .withExposedService("kafka", 9092)
                .withExposedService("zookeeper", 2181)
                .waitingFor("zookeeper", Wait.defaultWaitStrategy())
                .waitingFor("kafka", Wait.defaultWaitStrategy())
                .start()
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            instance.stop()
        }
    }

    @Test
    fun testProduceMessage() {
        kotlinProducer.send("test message")
        logger.info("i think ive sent a message")
    }
}

