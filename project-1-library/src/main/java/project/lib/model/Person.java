package project.lib.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {
    private int id;
    @NotBlank(message = "Full name must not be blank")
    private String fullName;
    @Min(value = 1900, message = "Year of birth must be greater than 1900")
    private int yearOfBirth;
}
