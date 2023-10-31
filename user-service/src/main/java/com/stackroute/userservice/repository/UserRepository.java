package com.stackroute.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.userservice.model.user.User;

@Repository
@Transactional
public interface UserRepository extends MongoRepository<User, String> {

}
