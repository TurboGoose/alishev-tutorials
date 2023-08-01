package ru.hibernate.schools;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int number;
    @OneToOne
    @JoinColumn(name = "principal_id", referencedColumnName = "id")
    private Principal principal;
}
