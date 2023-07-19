package ru.turbogoose.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.turbogoose.models.Person;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        return jdbcTemplate.query("SELECT * FROM person;", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> getPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?;", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findFirst();
    }

    public int save(Person person) {
        final String SQL = "INSERT INTO person(name, age, email) VALUES(?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setString(3, person.getEmail());
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
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?;",
                updated.getName(), updated.getAge(), updated.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?;", id);
    }
}
