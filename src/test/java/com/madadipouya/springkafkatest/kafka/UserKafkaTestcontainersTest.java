package com.madadipouya.springkafkatest.kafka;

import com.madadipouya.springkafkatest.dto.User;
import com.madadipouya.springkafkatest.kafka.consumer.UserKafkaConsumer;
import com.madadipouya.springkafkatest.kafka.producer.UserKafkaProducer;
import com.madadipouya.springkafkatest.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@Testcontainers
@SpringBootTest
class UserKafkaTestcontainersTest {

    @Container
    static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Autowired
    private UserKafkaProducer userKafkaProducer;

    @Autowired
    private UserKafkaConsumer userKafkaConsumer;

    @MockBean
    private UserService userService;

    @Test
    void testProduceAndConsumeKafkaMessage() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        User user = new User("11111", "John", "Wick");

        userKafkaProducer.writeToKafka(user);

        verify(userService, timeout(5000)).save(captor.capture());
        assertNotNull(captor.getValue());
        assertEquals("11111", captor.getValue().getUuid());
        assertEquals("John", captor.getValue().getFirstName());
        assertEquals("Wick", captor.getValue().getLastName());
    }
}
