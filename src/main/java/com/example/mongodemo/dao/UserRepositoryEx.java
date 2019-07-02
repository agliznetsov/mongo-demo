package com.example.mongodemo.dao;

import com.example.mongodemo.model.User;

import java.util.List;

public interface UserRepositoryEx {
    List<User> customFind();
}
