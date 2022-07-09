package com.madadipouya.springkafkatest.service.impl;

import com.madadipouya.springkafkatest.dto.User;
import com.madadipouya.springkafkatest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultUserService.class);

    @Override
    public void save(User user) {
        logger.info("Saving user with id = {}", user.getUuid());
    }
}
