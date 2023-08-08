package project.lib.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Title must not be blank")
    private String title;
    @NotBlank(message = "Author name must not be blank")
    private String author;
    @Min(value = 0, message = "Year of publication must be positive or zero")
    @Column(name = "year_of_publication")
    private int yearOfPublication;
    @Column(name = "borrowed_at")
    private LocalDateTime borrowedAt;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person borrower;

    public boolean isExpired() {
        if (borrowedAt == null) {
            return false;
        }
        return borrowedAt.plusDays(10).isBefore(LocalDateTime.now());
    }
}
