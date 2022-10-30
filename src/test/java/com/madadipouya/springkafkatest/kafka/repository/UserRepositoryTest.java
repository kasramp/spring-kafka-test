package com.madadipouya.springkafkatest.kafka.repository;

import com.madadipouya.springkafkatest.entity.User;
import com.madadipouya.springkafkatest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@DirtiesContext
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testGetByFirstName() {
        testEntityManager.persist(stubUser("john", "Wick"));
        testEntityManager.persist(stubUser("Robert", "McCall"));
        testEntityManager.persist(stubUser("John", "Rambo"));

        List<User> users = userRepository.getByFirstNameIgnoreCaseOrderByFirstNameAscLastNameAsc("John");

        assertFalse(users.isEmpty());
        assertEquals(2, users.size());
        assertEquals("John", users.get(0).getFirstName());
        assertEquals("Rambo", users.get(0).getLastName());
        assertEquals("john", users.get(1).getFirstName());
        assertEquals("Wick", users.get(1).getLastName());
    }

    private User stubUser(String firstName, String lastName) {
        return new User(UUID.randomUUID().toString(), firstName, lastName);
    }
}
