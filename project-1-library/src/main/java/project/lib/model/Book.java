package project.lib.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private int id;
    @NotBlank(message = "Title must not be blank")
    private String title;
    @NotBlank(message = "Author name must not be blank")
    private String author;
    @Min(value = 0, message = "Year of publication must be positive or zero")
    private int yearOfPublication;
}
