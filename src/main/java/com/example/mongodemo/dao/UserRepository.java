package com.example.mongodemo.dao;

import com.example.mongodemo.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, ObjectId>, UserRepositoryEx {
    User findByName(String name);
}
