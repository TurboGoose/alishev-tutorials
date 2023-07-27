package project.lib.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project.lib.model.Book;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksByPersonId(int personId) {
        final String SQL = "SELECT * FROM Book b join Person p on b.person_id = p.id WHERE person_id=?";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Book.class), personId);
    }
}
