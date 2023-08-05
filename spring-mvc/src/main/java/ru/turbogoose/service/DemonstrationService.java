package ru.turbogoose.service;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.turbogoose.model.Person;

import java.util.List;

@Service
public class DemonstrationService {
    private final EntityManager entityManager;

    public DemonstrationService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void demonstrateNPlusOneProblem() {
        showProblemCase();
        showSolution();
    }

    private void showProblemCase() {
        System.out.println("Problem case");
        Session session = entityManager.unwrap(Session.class);

        // 1 operation
        List<Person> people = session.createQuery("from Person", Person.class).getResultList();

        // N operations
        for (Person person : people) {
            System.out.println(person.getName() + ": " + person.getItems());
        }
    }

    private void showSolution() {
        System.out.println("Solution");
        Session session = entityManager.unwrap(Session.class);

        // 1 operation
        List<Person> people = session.createQuery("select p from Person p join fetch p.items", Person.class)
                .getResultList();

        for (Person person : people) {
            System.out.println(person.getName() + ": " + person.getItems());
        }
    }
}
