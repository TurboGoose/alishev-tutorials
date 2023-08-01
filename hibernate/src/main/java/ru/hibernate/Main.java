package ru.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.hibernate.items.model.Item;
import ru.hibernate.items.model.Person;
import ru.hibernate.items.util.ItemFactory;
import ru.hibernate.items.util.PersonFactory;

import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        try (SessionFactory sessionFactory = config.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = PersonFactory.get();
            System.out.println(person);
            for (int i = 0; i < 5; i++) {

                Item item = ItemFactory.get();
                System.out.println(item);
                person.addItem(item);
            }

            session.persist(person);

            session.getTransaction().commit();
        }
    }
}
