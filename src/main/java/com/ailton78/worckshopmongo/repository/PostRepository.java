package com.ailton78.worckshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ailton78.worckshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
