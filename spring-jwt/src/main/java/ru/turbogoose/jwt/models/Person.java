package ru.turbogoose.jwt.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    private String password;
    private String role;
}
