package ru.hibernate.util;

import ru.hibernate.model.Person;

import java.util.Random;

public class PersonFactory {
    private static int personCount = 1;
    private static Random random = new Random();

    public static Person get() {
        Person person = new Person();
        person.setName("Person #" + personCount++);
        person.setAge(random.nextInt(0, 80));
        return person;
    }
}
