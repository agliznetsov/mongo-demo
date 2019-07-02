package com.example.mongodemo.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
@Data
public class Production {
    @Id
    ObjectId id;

    @NotBlank
    String name;
}
