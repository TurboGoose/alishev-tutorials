package ru.turbogoose.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.turbogoose.dto.ErrorDto;
import ru.turbogoose.exceptions.SensorAlreadyExistsException;
import ru.turbogoose.exceptions.SensorNotExistsException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private Map<String, String> handleInvalidBinding(MethodArgumentNotValidException exc) {
        Map<String, String> errors = new HashMap<>();
        exc.getBindingResult().getFieldErrors().forEach(
                fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        return errors;
    }

    @ExceptionHandler({SensorNotExistsException.class, SensorAlreadyExistsException.class})
    private ResponseEntity<ErrorDto> handleNonExistentSensorException(SensorNotExistsException exc) {
        ErrorDto error = new ErrorDto(exc.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
