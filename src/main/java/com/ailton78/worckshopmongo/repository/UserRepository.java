package com.ailton78.worckshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ailton78.worckshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
