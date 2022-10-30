package com.madadipouya.springkafkatest.repository;

import com.madadipouya.springkafkatest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> getByFirstNameIgnoreCaseOrderByFirstNameAscLastNameAsc(String firstName);

}