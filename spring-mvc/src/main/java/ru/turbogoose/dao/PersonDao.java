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
        people.add(new Person(generateId(), 15, "anton@mail.com", "Anton"));
        people.add(new Person(generateId(), 21, "ilya@mail.com", "Ilya"));
        people.add(new Person(generateId(), 35, "andrey@mail.com", "Andrey"));
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

    public void update(int id, Person updatedPerson) {
        Optional<Person> optionalPersonToBeUpdated = people.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        if (optionalPersonToBeUpdated.isEmpty()) {
            return;
        }
        Person personToBeUpdated = optionalPersonToBeUpdated.get();
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
