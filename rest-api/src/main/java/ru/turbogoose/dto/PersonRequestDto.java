package ru.turbogoose.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonRequestDto {
    @Min(value = 0, message = "Age must be positive")
    private int age;
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 30, message = "Name length must be in between of 2 and 30 characters")
    private String name;
    @Email(message = "Email must be in valid format")
    @NotEmpty(message = "Email must not be empty")
    private String email;
}
