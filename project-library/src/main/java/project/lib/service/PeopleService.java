package project.lib.service;

import org.springframework.stereotype.Service;
import project.lib.model.Person;
import project.lib.repository.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getAllPeopleWithoutBooks() {
        return peopleRepository.findAll();
    }

    public Optional<Person> getPersonWithBooksById(int id) {
        return peopleRepository.findByIdWithBooks(id);
    }

    public Optional<Person> getPersonWithoutBooksById(int id) {
        return peopleRepository.findById(id);
    }

    public int savePerson(Person person) {
        peopleRepository.save(person);
        return person.getId();
    }

    public void updatePerson(Person person) {
        peopleRepository.save(person);
    }

    public void deletePersonById(int id) {
        peopleRepository.deleteById(id);
    }
}
