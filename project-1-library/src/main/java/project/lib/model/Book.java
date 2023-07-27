package project.lib.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
