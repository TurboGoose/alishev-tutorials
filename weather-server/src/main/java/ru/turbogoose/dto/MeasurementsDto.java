package ru.turbogoose.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MeasurementsDto {
    private List<MeasurementDto> measurements;
}
