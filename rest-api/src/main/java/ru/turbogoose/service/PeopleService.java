package ru.turbogoose.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.turbogoose.exceptions.PersonNotFoundException;
import ru.turbogoose.repository.PeopleRepository;
import ru.turbogoose.model.Person;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElseThrow(
                () -> new PersonNotFoundException(String.format("Person with id %d was not found", id))
        );
    }

    @Transactional
    public Person save(Person person) {
        enrichPerson(person);
        return peopleRepository.save(person);
    }

    private void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
    }
}
