package ru.turbogoose.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.turbogoose.exceptions.SensorNotExistsException;
import ru.turbogoose.models.Measurement;
import ru.turbogoose.models.Sensor;
import ru.turbogoose.repositories.MeasurementRepository;
import ru.turbogoose.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void registerMeasurement(Measurement measurement) {
        String sensorName = measurement.getSensor().getName();
        Sensor sensor = sensorRepository.findByName(sensorName)
                .orElseThrow(() -> new SensorNotExistsException(
                        String.format("Sensor with name '%s' does not exist", sensorName)));
        measurement.setSensor(sensor);
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
    }

    public Integer getRainyDaysCount() {
        return measurementRepository.countByRainingIsTrue();
    }
}
