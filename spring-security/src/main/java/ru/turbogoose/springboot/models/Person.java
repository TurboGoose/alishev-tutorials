package ru.turbogoose.springboot.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 2, max = 100, message = "Name must has length from 2 to 100 symbols")
    private String name;
    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Year of birth must be greater than 1900")
    private int yearOfBirth;
    private String password;
    private String role;
}
