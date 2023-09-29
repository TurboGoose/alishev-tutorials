package ru.turbogoose.dto;

import lombok.Data;

@Data
public class PersonResponseDto {
    private int id;
    private int age;
    private String name;
    private String email;
}
