package ru.turbogoose.springboot.services;

import org.springframework.stereotype.Service;
import ru.turbogoose.springboot.models.Person;
import ru.turbogoose.springboot.repositories.PeopleRepository;

import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> getPersonByName(String name) {
        return peopleRepository.findPersonByName(name);
    }
}
