package ru.turbogoose.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.turbogoose.dto.MeasurementDto;
import ru.turbogoose.models.Measurement;

@Component
@RequiredArgsConstructor
public class MeasurementMapper {
    private final ModelMapper modelMapper;

    public MeasurementDto toDto(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDto.class);
    }

    public Measurement toModel(MeasurementDto measurementDto) {
        return modelMapper.map(measurementDto, Measurement.class);
    }
}
