package project.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.lib.model.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE book SET person_id=:borrowerId WHERE id=:bookId")
    void updateBorrower(@Param("bookId") Integer bookId, @Param("borrowerId") Integer borrowerId);
}
