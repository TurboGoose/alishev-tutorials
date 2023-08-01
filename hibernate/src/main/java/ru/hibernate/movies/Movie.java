package ru.hibernate.movies;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;
    private String name;
    @Column(name = "year_of_production")
    private int yearOfProduction;

    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "director_id")
    private Director director;
}
