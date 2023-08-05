package ru.turbogoose.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;


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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "owner")
    private List<Item> items;
}
