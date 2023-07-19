package ru.turbogoose.dao;

import org.springframework.stereotype.Component;
import ru.turbogoose.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private static final String POSTGRES_USER = "ilya";
    private static final String POSTGRES_PASSWORD = "bebra";
    private static final String URL = "jdbc:postgresql://localhost:5432/crud";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, POSTGRES_USER, POSTGRES_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        final String sql = "SELECT * FROM person;";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                person.setAge(rs.getInt("age"));
                people.add(person);
            }

            return people;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Person> getPersonById(int id) {
        final String sql = "SELECT * FROM person WHERE id=?;";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return Optional.empty();
            }

            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setEmail(rs.getString("email"));
            person.setAge(rs.getInt("age"));

            return Optional.of(person);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int save(Person person) {
        final String sql = "INSERT INTO person(name, age, email) VALUES(?, ?, ?);";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setString(3, person.getEmail());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) {
                throw new RuntimeException("Key was not generated");
            }
            int generatedId = rs.getInt(1);
            person.setId(generatedId);
            return generatedId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updatedPerson) {
        final String sql = "UPDATE person SET name=?, age=?, email=? WHERE id=?;";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, updatedPerson.getName());
            ps.setInt(2, updatedPerson.getAge());
            ps.setString(3, updatedPerson.getEmail());
            ps.setInt(4, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        final String sql = "DELETE FROM person WHERE id=?;";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
