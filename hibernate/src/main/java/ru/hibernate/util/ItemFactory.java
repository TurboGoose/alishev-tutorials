package ru.hibernate.util;

import ru.hibernate.model.Item;
import ru.hibernate.model.Person;

import java.util.Random;

public class ItemFactory {
    private static final Random random = new Random();

    public static Item getItem(Person owner) {
        Item item = new Item();
        item.setName("Item #" + random.nextInt(0, 100));
        item.setOwner(owner);
        return item;
    }
}
