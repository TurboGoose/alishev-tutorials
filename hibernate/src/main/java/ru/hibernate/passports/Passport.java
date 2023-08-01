package ru.hibernate.passports;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
public class Passport {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Id
    @OneToOne
    @JoinColumn(name = "citizen_id", referencedColumnName = "id")
    private Citizen citizen;
    private int number;

    public Passport(int number) {
        this.number = number;
    }
}
