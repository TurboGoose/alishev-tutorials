package ru.turbogoose.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.turbogoose.models.Person;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private SessionFactory sessionFactory;

    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> getAllPeople() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Person", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<Person> getPersonById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Person.class, id));
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update(Person updated) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(updated);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.remove(person);
    }

    @Transactional(readOnly = true)
    public Optional<Person> getPersonByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        try {
            return Optional.of(session.createQuery("from Person where email=:email", Person.class)
                    .setParameter("email", email)
                    .getSingleResult());
        } catch (NoResultException exc) {
            return Optional.empty();
        }
    }
}

