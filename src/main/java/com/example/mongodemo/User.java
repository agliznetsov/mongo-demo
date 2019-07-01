package com.example.mongodemo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document
@Data
@Builder
public class User {
	@Id
	String id;

	@NotBlank
	@Size(min = 3)
	@Indexed(unique = true)
	String name;

	String email;
}
