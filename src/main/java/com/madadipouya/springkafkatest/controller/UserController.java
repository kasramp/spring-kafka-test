package com.madadipouya.springkafkatest.controller;

import com.github.javafaker.Faker;
import com.madadipouya.springkafkatest.dto.User;
import com.madadipouya.springkafkatest.kafka.producer.UserKafkaProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("v1/users")
@Api("User APIs")
public class UserController {

    private final UserKafkaProducer kafkaProducer;

    private final Faker faker;

    public UserController(UserKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        faker = new Faker();
    }

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Creates a random user and write it to Kafka which is consumed by the listener")
    public void generateRandomUser() {
        kafkaProducer.writeToKafka(new User(UUID.randomUUID().toString(), faker.name().firstName(), faker.name().lastName()));
    }
}
