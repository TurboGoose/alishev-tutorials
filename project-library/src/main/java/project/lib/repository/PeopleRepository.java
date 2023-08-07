package project.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.lib.model.Person;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    @Query("FROM Person p JOIN FETCH p.books WHERE p.id = (:id)")
    Optional<Person> findByIdWithBooks(int id);
}
