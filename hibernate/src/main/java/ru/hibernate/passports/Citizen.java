package ru.hibernate.passports;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    @OneToOne(mappedBy = "citizen", cascade = CascadeType.PERSIST)
    private Passport passport;

    public Citizen(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
