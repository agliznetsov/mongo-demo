package com.example.mongodemo.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Document
@TypeAlias("User")
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
