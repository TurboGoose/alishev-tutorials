package ru.turbogoose.exceptions;

public class PersonValidationException extends RuntimeException {
    public PersonValidationException(String message) {
        super(message);
    }
}
