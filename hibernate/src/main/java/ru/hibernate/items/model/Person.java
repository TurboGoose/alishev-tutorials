package ru.hibernate.items.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private List<Item> items;

    public void addItem(Item item) {
        if (item == null) {
            return;
        }
        if (items == null) {
            this.items = new ArrayList<>();
        }
        items.add(item);
        item.setOwner(this);
    }
}
