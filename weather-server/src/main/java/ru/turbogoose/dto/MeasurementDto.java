package ru.turbogoose.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MeasurementDto {
    @NotNull(message = "Value is required")
    @Min(value = -100, message = "Value must be greater or equal to -100")
    @Max(value = 100, message = "Value must be lower or equal to 100")
    private Double value;
    @NotNull(message = "Raining is required")
    private Boolean raining;
    @Valid
    @NotNull(message = "Sensor is required")
    private SensorDto sensor;
}
