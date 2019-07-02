package com.example.mongodemo.dao;

import com.example.mongodemo.model.Production;
import com.example.mongodemo.model.User;
import com.example.mongodemo.model.UserProduction;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductionRepository extends CrudRepository<UserProduction, ObjectId> {
    List<UserProduction> findByUser(User user);

    List<UserProduction> findByProduction(Production production);
}
