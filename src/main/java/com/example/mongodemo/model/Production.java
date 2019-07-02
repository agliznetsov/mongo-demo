package com.example.mongodemo.model;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Production {
	@Id
	ObjectId id;

	@NotBlank
	String name;
}
