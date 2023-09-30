package models;

import lombok.Data;

import java.util.List;

@Data
public class MeasurementsResponse {
    private List<Measurement> measurements;
}
