package com.madadipouya.springkafkatest.kafka.consumer;

import com.madadipouya.springkafkatest.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(UserKafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            concurrency = "${spring.kafka.consumer.level.concurrency:3}")
    public void logKafkaMessages(@Payload User user,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                 @Header(KafkaHeaders.OFFSET) Long offset) {
        logger.info("Received a message contains a user information with id {}, from {} topic, " +
                        "{} partition, and {} offset", user.getUuid(), topic, partition, offset);
    }
}
