package com.example.mongodemo.dao;

import com.example.mongodemo.model.Production;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionRepository extends CrudRepository<Production, ObjectId> {
}
