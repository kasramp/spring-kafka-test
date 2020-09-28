package com.madadipouya.springkafkatest.producer;

import com.madadipouya.springkafkatest.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topic;

    public UserKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void writeToKafka(User user) {
        kafkaTemplate.send(topic, user.getUuid(), user);
    }
}
