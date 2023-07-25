package ru.turbogoose.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.turbogoose.models.Person;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPeople() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> getPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findFirst();
    }

    public Optional<Person> getPersonByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM person WHERE email=?", new BeanPropertyRowMapper<>(Person.class), email)
                .stream().findFirst();
    }

    public int save(Person person) {
        final String SQL = "INSERT INTO person(name, age, email, address) VALUES(?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setString(3, person.getEmail());
            ps.setString(4, person.getAddress());
            return ps;
        }, keyHolder);

        int generatedId = extractId(keyHolder);
        person.setId(generatedId);
        return generatedId;
    }

    private int extractId(KeyHolder keyHolder) {
        Map<String, Object> generatedKeys = keyHolder.getKeys();
        if (generatedKeys == null || !generatedKeys.containsKey("id")) {
            throw new RuntimeException("Id was not generated");
        }
        return (int) generatedKeys.get("id");
    }

    public void update(int id, Person updated) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=?, address=? WHERE id=?",
                updated.getName(), updated.getAge(), updated.getEmail(), updated.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    ///////////////////////////////
    /////// Test batching
    ///////////////////////////////

    private final int PEOPLE_COUNT = 1000;

    public long updateWithBatching() {
        List<Person> people = generateXPeople();
        BatchPreparedStatementSetter batchPreparedStatementSetter = new PeopleBatchPreparedStatementSetter(people);

        long start = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO person(name, age, email, address) VALUES(?, ?, ?, ?)",
                batchPreparedStatementSetter);

        long end = System.currentTimeMillis();
        return end - start;
    }

    public long updateWithoutBatching() {
        List<Person> people = generateXPeople();

        long start = System.currentTimeMillis();

        for (Person person : people) {
            jdbcTemplate.update("INSERT INTO person(name, age, email, address) VALUES(?, ?, ?, ?)",
                    person.getName(), person.getAge(), person.getEmail(), person.getAddress());
        }

        long end = System.currentTimeMillis();
        return end - start;
    }

    private List<Person> generateXPeople() {
        List<Person> people = new ArrayList<>(PEOPLE_COUNT);
        for (int i = 0; i < PEOPLE_COUNT; i++) {
            people.add(new Person(20, "person" + i + "@mail.com", "Person " + i, "Kolotushkina 5"));
        }
        return people;
    }
}

