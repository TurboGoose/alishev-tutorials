package ru.turbogoose.dao;

import org.springframework.stereotype.Component;
import ru.turbogoose.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private static int ID_COUNTER = 1;
    private final List<Person> people;

    public static int generateId() {
        return ID_COUNTER++;
    }

    public PersonDao() {
        people = new ArrayList<>();
        people.add(new Person(generateId(), "Anton"));
        people.add(new Person(generateId(), "Ilya"));
        people.add(new Person(generateId(), "Andrey"));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Optional<Person> getPersonById(int id) {
        return people.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public void save(Person person) {
        person.setId(generateId());
        people.add(person);
    }
}
