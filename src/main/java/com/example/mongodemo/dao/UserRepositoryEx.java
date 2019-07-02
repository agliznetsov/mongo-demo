package com.example.mongodemo.dao;

import java.util.List;

import com.example.mongodemo.model.User;

public interface UserRepositoryEx {
	List<User> customFind();
}
