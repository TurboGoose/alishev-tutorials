package ru.turbogoose.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.turbogoose.models.Person;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findPersonByName(String name);
}
