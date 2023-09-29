package ru.turbogoose.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.turbogoose.dto.MeasurementDto;
import ru.turbogoose.mappers.MeasurementMapper;
import ru.turbogoose.services.MeasurementService;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final MeasurementMapper measurementMapper;
    private final MeasurementService measurementService;

    @PostMapping
    public ResponseEntity<Void> registerMeasurement(@RequestBody @Valid MeasurementDto measurementDto) {
        measurementService.registerMeasurement(measurementMapper.toModel(measurementDto));
        return ResponseEntity.ok().build();
    }
}
