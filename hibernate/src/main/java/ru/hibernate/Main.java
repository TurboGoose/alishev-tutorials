package ru.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.hibernate.model.Item;
import ru.hibernate.model.Person;
import ru.hibernate.util.ItemFactory;
import ru.hibernate.util.PersonFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        try (SessionFactory sessionFactory = config.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            getItemsByOwner(session);
//            getOwnerByItem(session);
//            addItemForPerson(session);
//            addPersonAndItem(session);
//            deleteItemsOfPerson(session);
//            deletePerson(session);
            changeOwnerForItem(session);

            session.getTransaction().commit();
        }
    }

    private static void changeOwnerForItem(Session session) {
        Item item = session.get(Item.class, 1); // select
        Person oldOwner = item.getOwner(); // not cached
        Person newOwner = session.get(Person.class, 3); // select -> cached?

        item.setOwner(newOwner); // update
        oldOwner.getItems().remove(item); // not cached -> select
        newOwner.getItems().add(item); // cached -> no select
    }

    private static void deletePerson(Session session) {
        Person person = session.get(Person.class, 1);

        System.out.println("Before removal");
        session.remove(person);
        System.out.println("After removal");

//        System.out.println("All owners are nulls: " + areOwnersNulls(person));

        person.getItems().forEach(p -> p.setOwner(null));
        System.out.println("All owners are nulls: " + areOwnersNulls(person));
    }

    private static boolean areOwnersNulls(Person person) {
        return person.getItems().stream()
                .map(Item::getOwner)
                .allMatch(Objects::isNull);
    }


    private static void deleteItemsOfPerson(Session session) {
        Person person = session.get(Person.class, 1);
        for (Item item : person.getItems()) {
            session.remove(item);
        }
        person.getItems().clear();
        System.out.println(person.getItems());
    }

    private static void getItemsByOwner(Session session) {
        Person person = session.get(Person.class, 1);
        System.out.println(person);

        System.out.println(person.getItems());
    }

    private static void getOwnerByItem(Session session) {
        Item item = session.get(Item.class, 1);
        System.out.println(item);

        System.out.println(item.getOwner());
    }

    private static void addItemForPerson(Session session) {
        Person person = session.get(Person.class, 3);
        System.out.println(person);

        Item item = ItemFactory.getItem(person);

        session.persist(item);
        System.out.println(item + "; " + item.getOwner());
        System.out.println(person.getItems());
    }

    private static void addPersonAndItem(Session session) {
        Person person = PersonFactory.get();
        Item item = ItemFactory.getItem(person);
        person.setItems(new ArrayList<>(List.of(item)));

        session.persist(person);
        session.persist(item);

        System.out.println(person);
        System.out.println(item);
    }
}
