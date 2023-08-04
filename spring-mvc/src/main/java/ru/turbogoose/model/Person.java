package ru.turbogoose.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Min(value = 0, message = "Age must be positive")
    private int age;
    @NotEmpty
    @Size(min = 2, max = 30, message = "Name length must be in between 2 and 30 characters")
    private String name;
    @Email(message = "Email must be valid")
    private String email;
}
