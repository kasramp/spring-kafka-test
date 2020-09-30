package com.madadipouya.springkafkatest.kafka.producer;

import com.madadipouya.springkafkatest.dto.User;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topic;

    @Value("${spring.kafka.replication.factor:1}")
    private int replicationFactor;

    @Value("${spring.kafka.partition.number:1}")
    private int partitionNumber;

    public UserKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void writeToKafka(User user) {
        kafkaTemplate.send(topic, user.getUuid(), user);
    }

    @Bean
    @Order(-1)
    public NewTopic createNewTopic() {
        return new NewTopic(topic, partitionNumber, (short) replicationFactor);
    }
}
