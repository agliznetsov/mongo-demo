package com.example.mongodemo.dao;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.mongodemo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,ObjectId>, UserRepositoryEx {
	User findByName(String name);
}
