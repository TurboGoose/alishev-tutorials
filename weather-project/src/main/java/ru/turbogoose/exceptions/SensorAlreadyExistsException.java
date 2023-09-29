package ru.turbogoose.exceptions;

public class SensorAlreadyExistsException extends RuntimeException {
    public SensorAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
