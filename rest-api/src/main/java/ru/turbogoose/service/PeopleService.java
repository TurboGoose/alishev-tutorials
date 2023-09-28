package ru.turbogoose.service;

import org.springframework.stereotype.Service;
import ru.turbogoose.repository.PeopleRepository;
import ru.turbogoose.model.Person;

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
        return peopleRepository.findById(id).orElse(null);
    }
}
