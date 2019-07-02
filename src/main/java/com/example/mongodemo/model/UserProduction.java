package com.example.mongodemo.model;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
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
