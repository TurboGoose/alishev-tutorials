package ru.turbogoose.services;

import org.springframework.stereotype.Service;
import ru.turbogoose.repositories.PeopleRepository;
import ru.turbogoose.models.Person;

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
