package project.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.lib.model.Book;
import project.lib.model.Person;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
