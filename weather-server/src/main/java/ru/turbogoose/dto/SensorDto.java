package ru.turbogoose.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorDto {
    @NotNull(message = "Sensor name is required")
    @Size(min = 3, max = 30, message = "Sensor name length must be in between of 3 and 30 symbols")
    private String name;
}
