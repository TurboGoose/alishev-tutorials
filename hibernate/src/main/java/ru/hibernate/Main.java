package ru.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.hibernate.model.Person;

class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration().addAnnotatedClass(Person.class);

        try (SessionFactory sessionFactory = config.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            Person person = session.get(Person.class, 1);
            session.getTransaction().commit();

            System.out.println(person);
        }
    }
}
