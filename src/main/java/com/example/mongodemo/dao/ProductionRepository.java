package com.example.mongodemo.dao;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.mongodemo.model.Production;

@Repository
public interface ProductionRepository extends CrudRepository<Production,ObjectId> {
}
