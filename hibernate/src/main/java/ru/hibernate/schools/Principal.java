package ru.hibernate.schools;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(mappedBy = "principal", cascade = CascadeType.PERSIST)
    private School school;
}
