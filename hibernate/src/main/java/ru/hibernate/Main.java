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

            Person person = PersonFactory.get();

            // create
            session.persist(person);
            int id = person.getId();
            System.out.println("Generated id: " + id);

            // read
            person = session.get(Person.class, id);
            System.out.println("Loaded person: " + person);

            // update
            person.setName("#" + person.getName());
            System.out.println("After update");

            // delete
//            session.remove(person);

            session.getTransaction().commit();
            System.out.println("After transaction");
        }
    }
}
