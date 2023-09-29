package ru.turbogoose.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.turbogoose.dto.ErrorDto;
import ru.turbogoose.dto.SensorDto;
import ru.turbogoose.exceptions.SensorAlreadyExistsException;
import ru.turbogoose.mappers.SensorMapper;
import ru.turbogoose.services.SensorService;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorsController {
    private final SensorMapper sensorMapper;
    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDto sensorDto) {
        sensorService.registerSensor(sensorMapper.toModel(sensorDto));
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(SensorAlreadyExistsException.class)
    private ResponseEntity<ErrorDto> handleDuplicateSensorException(SensorAlreadyExistsException exc) {
        ErrorDto error = new ErrorDto(exc.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
