package com.madadipouya.springkafkatest.service;

import com.madadipouya.springkafkatest.dto.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<com.madadipouya.springkafkatest.entity.User> getUsers(String firstName);
}
