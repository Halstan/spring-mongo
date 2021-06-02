package com.course.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Document
public class Student {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    private Gender gender;

    private Address address;

    private List<String> favoritesSubjects;

    private Double totalSpentInBooks;

    private LocalDateTime createdAt;

    public Student(String firstName, String lastName, String email, Gender gender, Address address, List<String> favoritesSubjects, Double totalSpentInBooks, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favoritesSubjects = favoritesSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.createdAt = createdAt;
    }

}
