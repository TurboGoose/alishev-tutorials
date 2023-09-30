package utils;

import models.Measurement;
import models.Sensor;

import java.util.Random;

public class MeasurementFactory {
    private final Random random = new Random();
    private final SensorPool sensorPool;

    public MeasurementFactory(SensorPool sensorPool) {
        this.sensorPool = sensorPool;
    }

    public Measurement getMeasurement() {
        double value = random.nextDouble(-100, 100);
        boolean raining = random.nextBoolean();
        Sensor sensor = sensorPool.getRandomSensor();
        return new Measurement(value, raining, sensor);
    }
}
