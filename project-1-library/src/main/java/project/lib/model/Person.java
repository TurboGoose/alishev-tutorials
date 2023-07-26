package project.lib.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {
    private int id;
    private String fullName;
    private int yearOfBirth;
}
