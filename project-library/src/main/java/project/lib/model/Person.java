package project.lib.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Full name must not be blank")
    @Column(name = "full_name")
    private String fullName;
    @Min(value = 1900, message = "Year of birth must be greater than 1900")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "borrower")
    private List<Book> books;
}
