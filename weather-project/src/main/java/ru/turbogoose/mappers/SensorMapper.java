package ru.turbogoose.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.turbogoose.dto.SensorDto;
import ru.turbogoose.models.Sensor;

@Component
@RequiredArgsConstructor
public class SensorMapper {
    private final ModelMapper modelMapper;

    public SensorDto toDto(Sensor sensor) {
        return modelMapper.map(sensor, SensorDto.class);
    }

    public Sensor toModel(SensorDto sensorDto) {
        return modelMapper.map(sensorDto, Sensor.class);
    }
}
