package project.lib.service;

import jakarta.persistence.EntityManager;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.lib.model.Person;
import project.lib.repository.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final EntityManager entityManager;

    public PeopleService(PeopleRepository peopleRepository, EntityManager entityManager) {
        this.peopleRepository = peopleRepository;
        this.entityManager = entityManager;
    }

    public List<Person> getAllPeopleWithoutBooks() {
        return peopleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Person> getPersonWithBooksById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Person person = session.get(Person.class, id);
        if (person == null) {
            return Optional.empty();
        }
        Hibernate.initialize(person.getBooks());
        return Optional.of(person);
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
