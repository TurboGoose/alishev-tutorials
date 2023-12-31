package ru.turbogoose.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.turbogoose.model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
