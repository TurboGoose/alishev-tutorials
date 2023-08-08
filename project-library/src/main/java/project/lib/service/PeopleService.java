package project.lib.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(readOnly = true)
    public Optional<Person> getPersonWithBooksById(int id) {
        Optional<Person> optionalPerson = peopleRepository.findById(id);
        optionalPerson.ifPresent(p -> Hibernate.initialize(p.getBooks()));
        return optionalPerson;
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return peopleRepository.findByFullName(fullName);
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
