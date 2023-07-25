package collections;

import java.util.*;

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Person> peopleList = new ArrayList<>();
        Set<Person> peopleSet = new TreeSet<>();

        addElements(peopleList);
        addElements(peopleSet);

//        System.out.println(peopleSet);

        peopleList.sort(Comparator.comparingInt(p -> p.id));
        System.out.println(peopleList);
    }

    private static void addElements(Collection<Person> collection) {
        collection.add(new Person(4, "Anton"));
        collection.add(new Person(1, "Ilya"));
        collection.add(new Person(8, "Sasha"));
        collection.add(new Person(17, "Bebrik"));
    }
}

class Person implements Comparable<Person> {
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Person o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return this.name.compareTo(o.name);
    }
}
