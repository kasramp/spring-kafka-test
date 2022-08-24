package com.madadipouya.springkafkatest.repository;

import com.madadipouya.springkafkatest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}