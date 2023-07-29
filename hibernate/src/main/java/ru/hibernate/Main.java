package ru.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.hibernate.model.Person;
import ru.hibernate.util.PersonFactory;

class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration().addAnnotatedClass(Person.class);

        try (SessionFactory sessionFactory = config.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            for (int i = 0; i < 5; i++) {
                int id = (int) session.save(PersonFactory.get());
                System.out.println("Generated id: " + id);
            }

            session.getTransaction().commit();
        }
    }
}
