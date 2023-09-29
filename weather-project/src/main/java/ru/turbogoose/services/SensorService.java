package ru.turbogoose.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.turbogoose.exceptions.SensorAlreadyExistsException;
import ru.turbogoose.models.Sensor;
import ru.turbogoose.repositories.SensorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Transactional
    public void registerSensor(Sensor sensor) {
        try {
            sensorRepository.save(sensor);
        } catch (DataIntegrityViolationException exc) {
            throw new SensorAlreadyExistsException(
                    String.format("Sensor with name '%s' already exists", sensor.getName()));
        }
    }
}
