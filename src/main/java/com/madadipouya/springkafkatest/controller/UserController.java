package com.madadipouya.springkafkatest.controller;

import com.github.javafaker.Faker;
import com.madadipouya.springkafkatest.dto.User;
import com.madadipouya.springkafkatest.kafka.producer.UserKafkaProducer;
import com.madadipouya.springkafkatest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/users")
@Api("User APIs")
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
    @ApiOperation(value = "Creates a random user and write it to Kafka which is consumed by the listener")
    public void generateRandomUser() {
        kafkaProducer.writeToKafka(new User(UUID.randomUUID().toString(), faker.name().firstName(), faker.name().lastName()));
    }

    @GetMapping("/{firstName}")
    @ResponseStatus
    @ApiOperation(value = "Returns a list of users that matchers the given name")
    public List<User> getUsers(@PathVariable(name = "firstName") String name) {
        List<com.madadipouya.springkafkatest.entity.User> users = userService.getUsers(name);
        return users.stream().map(user -> new User(user.getId(), user.getFirstName(), user.getLastName())).collect(Collectors.toList());
    }
}
