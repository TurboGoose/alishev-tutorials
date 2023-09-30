package ru.turbogoose.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.turbogoose.dto.MeasurementDto;
import ru.turbogoose.dto.MeasurementsDto;
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

    @GetMapping
    public MeasurementsDto getAllMeasurements() {
        return new MeasurementsDto(measurementService.getAllMeasurements().stream()
                .map(measurementMapper::toDto)
                .toList());
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }
}
