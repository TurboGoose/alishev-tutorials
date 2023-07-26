package project.lib.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import project.lib.model.Person;

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
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public int save(Person person) {
        final String SQL = "INSERT INTO Person(full_name, year_of_birth) VALUES(?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getFullName());
            ps.setInt(2, person.getYearOfBirth());
            return ps;
        }, keyHolder);

        int generatedId = extractGeneratedId(keyHolder, "id");
        person.setId(generatedId);
        return generatedId;
    }

    private int extractGeneratedId(KeyHolder keyHolder, String idFieldName) {
        Map<String, Object> generatedKeys = keyHolder.getKeys();
        if (generatedKeys == null || !generatedKeys.containsKey(idFieldName)) {
            throw new RuntimeException("Id was not generated");
        }
        return (int) generatedKeys.get(idFieldName);
    }

    public Optional<Person> getPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findFirst();
    }
}
