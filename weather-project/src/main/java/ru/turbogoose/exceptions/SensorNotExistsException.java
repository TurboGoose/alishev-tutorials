package ru.turbogoose.exceptions;

public class SensorNotExistsException extends RuntimeException {
    public SensorNotExistsException(String message) {
        super(message);
    }
}
