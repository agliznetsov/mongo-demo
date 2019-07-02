package com.example.mongodemo.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@Data
@TypeAlias("UserProduction")
public class UserProduction {
    @Id
    ObjectId id;

    @NotNull
    @DBRef
    User user;

    @NotNull
    @DBRef
    Production production;
}
