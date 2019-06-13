package com.stackroute.favouriteservice.repository;

import com.stackroute.favouriteservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserArticleRepository extends MongoRepository<User, String> {

    public User findByUserName(String userName);

}
