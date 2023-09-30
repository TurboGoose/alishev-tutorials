package ru.turbogoose.jwt.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticationDto {
    @Size(min = 2, max = 100, message = "Name must has length from 2 to 100 symbols")
    private String name;
    private String password;
}
