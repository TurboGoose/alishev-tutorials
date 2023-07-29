package ru.hibernate.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "person_id_seq_generator")
    @SequenceGenerator(name = "person_id_seq_generator",
            sequenceName = "person_id_seq", allocationSize = 1)
    private int id;
    private String name;
    private int age;
}
