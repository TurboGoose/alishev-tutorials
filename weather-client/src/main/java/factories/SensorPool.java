package factories;

import models.Sensor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SensorPool implements Iterable<Sensor> {
    private final Random random = new Random();
    private final int sensorCount;
    private final List<Sensor> sensors;

    public SensorPool(int sensorCount) {
        this.sensorCount = sensorCount;
        sensors = new ArrayList<>(sensorCount);
        generateSensors();
    }

    public Sensor getRandomSensor() {
        return sensors.get(random.nextInt(sensors.size()));
    }

    private void generateSensors() {
        for (int i = 1; i <= sensorCount; i++) {
            sensors.add(new Sensor("Sensor#" + i));
        }
    }

    @Override
    public Iterator<Sensor> iterator() {
        return sensors.iterator();
    }
}
