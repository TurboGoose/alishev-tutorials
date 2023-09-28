package ru.turbogoose.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Min(value = 0, message = "Age must be positive")
    private int age;
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 30, message = "Name length must be in between of 2 and 30 characters")
    private String name;
    @Email(message = "Email must be in valid format")
    @NotEmpty(message = "Email must not be empty")
    private String email;
}
