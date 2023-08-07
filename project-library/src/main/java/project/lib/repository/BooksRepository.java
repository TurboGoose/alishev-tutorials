package project.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.lib.model.Book;
import project.lib.model.Person;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    @Modifying
    @Query("UPDATE Book SET borrower=:borrower WHERE id=:id")
    void updateBorrower(@Param("id") int bookId, @Param("borrower") Person borrower);
}
