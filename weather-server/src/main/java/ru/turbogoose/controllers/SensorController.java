package ru.turbogoose.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.turbogoose.dto.SensorDto;
import ru.turbogoose.mappers.SensorMapper;
import ru.turbogoose.services.SensorService;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorMapper sensorMapper;
    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity<Void> registerSensor(@RequestBody @Valid SensorDto sensorDto) {
        sensorService.registerSensor(sensorMapper.toModel(sensorDto));
        return ResponseEntity.ok().build();
    }
}
