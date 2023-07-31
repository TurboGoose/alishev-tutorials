package ru.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.hibernate.model.Person;

import java.util.List;

class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration().addAnnotatedClass(Person.class);
        try (SessionFactory sessionFactory = config.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            List<Person> people = session.createQuery("FROM Person WHERE age < 30", Person.class).getResultList();
            people.forEach(System.out::println);

            session.createQuery("update Person set age = 18 where age < 18 and age >= 14", Person.class).executeUpdate();

            session.createQuery("delete from Person where age < 14", Person.class).executeUpdate();

            session.getTransaction().commit();
        }
    }
}
