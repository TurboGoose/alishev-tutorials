import utils.MeasurementFactory;
import utils.SensorPool;
import models.Measurement;
import models.MeasurementsResponse;
import models.Sensor;
import org.springframework.web.client.RestTemplate;

public class RestClientApp {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SensorPool sensorPool = new SensorPool(10);
        registerSensors(sensorPool);

        MeasurementFactory factory = new MeasurementFactory(sensorPool);
        registerMeasurements(factory, 10);

        System.out.println("Rainy days count: " + getRainyDaysCount());

        System.out.println("Measurements:");
        getAllMeasurements().getMeasurements().forEach(System.out::println);
    }


    private static void registerSensors(SensorPool sensorPool) {
        String url = "http://localhost:8080/sensors";
        for (Sensor sensor : sensorPool) {
            restTemplate.postForObject(url, sensor, Void.class);
            System.out.println(sensor.getName() + " registered");
        }
    }

    private static void registerMeasurements(MeasurementFactory factory, int count) {
        String url = "http://localhost:8080/measurements";
        for (int i = 0; i < count; i++) {
            Measurement measurement = factory.getMeasurement();
            restTemplate.postForObject(url, measurement, Void.class);
            System.out.println(measurement + " registered");
        }
    }

    private static Integer getRainyDaysCount() {
        String url = "http://localhost:8080/measurements/rainyDaysCount";
        return restTemplate.getForObject(url, Integer.class);
    }

    private static MeasurementsResponse getAllMeasurements() {
        String url = "http://localhost:8080/measurements";
        return restTemplate.getForObject(url, MeasurementsResponse.class);
    }
}
