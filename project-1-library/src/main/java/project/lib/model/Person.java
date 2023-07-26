package project.lib.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class Person {
    private int id;
    @NotBlank(message = "Full name must not be blank")
    private String fullName;
    @Min(value = 1900, message = "Year of birth must be greater than 1900")
    private int yearOfBirth;
}
