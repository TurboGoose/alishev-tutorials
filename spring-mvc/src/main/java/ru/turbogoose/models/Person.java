package ru.turbogoose.models;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @Min(value = 0, message = "Age must be positive")
    private int age;
    @Email(message = "Email must be valid")
    private String email;
    @NotEmpty
    @Size(min = 2, max = 30, message = "Name length must be in between 2 and 30 characters")
    private String name;
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}$", message = "Address must be in format: Country, City, zip (6 digits)")
    private String address;

    public Person() {
    }

    public Person(int id, int age, String email, String name, String address) {
        this.id = id;
        this.age = age;
        this.email = email;
        this.name = name;
        this.address = address;
    }

    public Person(int age, String email, String name, String address) {
        this.age = age;
        this.email = email;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
