package com.example.mongodemo.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class User {
	@Id
	ObjectId id;

	@NotBlank
	@Size(min = 3)
	@Indexed(unique = true)
	String name;

	String email;

	Set<ApplicationRole> roles = new HashSet<>();
}
