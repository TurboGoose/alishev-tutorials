package ru.hibernate.items.util;

import ru.hibernate.items.model.Person;
import ru.hibernate.items.model.Item;

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
