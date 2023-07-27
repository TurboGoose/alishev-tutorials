package project.lib.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import project.lib.model.Book;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> getBooksByPersonId(int personId) {
        final String SQL = "SELECT * FROM Book b join Person p on b.person_id = p.id WHERE person_id=?";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Book.class), personId);
    }

    public int save(Book book) {
        final String SQL = "INSERT INTO Book(title, author, year_of_publication) VALUES(?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYearOfPublication());
            return ps;
        }, keyHolder);

        int generatedId = extractGeneratedId(keyHolder, "id");
        book.setId(generatedId);
        return generatedId;
    }

    private int extractGeneratedId(KeyHolder keyHolder, String idFieldName) {
        Map<String, Object> generatedKeys = keyHolder.getKeys();
        if (generatedKeys == null || !generatedKeys.containsKey(idFieldName)) {
            throw new RuntimeException("Id was not generated");
        }
        return (int) generatedKeys.get(idFieldName);
    }

    public Optional<Book> getBookById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findFirst();
    }
}
