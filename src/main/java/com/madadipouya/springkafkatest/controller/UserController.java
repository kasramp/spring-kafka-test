package com.madadipouya.springkafkatest.controller;

import com.github.javafaker.Faker;
import com.madadipouya.springkafkatest.dto.User;
import com.madadipouya.springkafkatest.kafka.producer.UserKafkaProducer;
import com.madadipouya.springkafkatest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "User", description = "User APIs")
public class UserController {

    private final UserKafkaProducer kafkaProducer;

    private final UserService userService;

    private final Faker faker;

    public UserController(UserKafkaProducer kafkaProducer, UserService userService) {
        this.kafkaProducer = kafkaProducer;
        this.userService = userService;
        faker = new Faker();
    }

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Create a user", description = "Creates a random user and write it to Kafka which is consumed by the listener")
    public void generateRandomUser() {
        kafkaProducer.writeToKafka(new User(UUID.randomUUID().toString(), faker.name().firstName(), faker.name().lastName()));
    }

    @GetMapping("/{firstName}")
    @ResponseStatus
    @Operation(summary = "Create a user", description = "Returns a list of users that matchers the given name")
    public List<User> getUsers(@PathVariable(name = "firstName") String name) {
        List<com.madadipouya.springkafkatest.entity.User> users = userService.getUsers(name);
        return users.stream().map(user -> new User(user.getId(), user.getFirstName(), user.getLastName())).collect(Collectors.toList());
    }
}
