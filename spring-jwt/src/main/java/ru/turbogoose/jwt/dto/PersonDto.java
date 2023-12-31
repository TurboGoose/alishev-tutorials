package ru.turbogoose.jwt.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonDto {
    @Size(min = 2, max = 100, message = "Name must has length from 2 to 100 symbols")
    private String name;
    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Year of birth must be greater than 1900")
    private int yearOfBirth;
    private String password;
}
