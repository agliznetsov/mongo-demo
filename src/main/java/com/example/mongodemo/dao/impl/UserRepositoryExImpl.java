package com.example.mongodemo.dao.impl;

import com.example.mongodemo.dao.UserRepositoryEx;
import com.example.mongodemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

public class UserRepositoryExImpl implements UserRepositoryEx {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<User> customFind() {
        return mongoOperations.findAll(User.class);
    }

}
