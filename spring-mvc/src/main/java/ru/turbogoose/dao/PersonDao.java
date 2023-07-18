package ru.turbogoose.dao;

import org.springframework.stereotype.Component;
import ru.turbogoose.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final List<Person> people;

    public PersonDao() {
        people = new ArrayList<>();
        people.add(new Person(1, "Anton"));
        people.add(new Person(2, "Ilya"));
        people.add(new Person(3, "Andrey"));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Optional<Person> getPersonById(int id) {
        return people.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }
}
