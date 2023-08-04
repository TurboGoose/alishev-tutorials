package ru.turbogoose.service;

import org.springframework.stereotype.Service;
import ru.turbogoose.model.Person;
import ru.turbogoose.repository.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Optional<Person> findById(int id) {
        return peopleRepository.findById(id);
    }

    public void save(Person person) {
        peopleRepository.save(person);
    }

    public void update(Person person) {
        peopleRepository.save(person);
    }

    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
