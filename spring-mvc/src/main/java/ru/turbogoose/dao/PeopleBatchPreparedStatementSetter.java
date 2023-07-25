package ru.turbogoose.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import ru.turbogoose.models.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

class PeopleBatchPreparedStatementSetter implements BatchPreparedStatementSetter {
    private final List<Person> people;

    public PeopleBatchPreparedStatementSetter(List<Person> people) {
        this.people = people;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        Person person = people.get(i);
        ps.setString(1, person.getName());
        ps.setInt(2, person.getAge());
        ps.setString(3, person.getEmail());
        ps.setString(4, person.getAddress());
    }

    @Override
    public int getBatchSize() {
        return people.size();
    }
}
