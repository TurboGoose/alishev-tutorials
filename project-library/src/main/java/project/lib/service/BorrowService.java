package project.lib.service;

import org.springframework.stereotype.Service;
import project.lib.model.Person;
import project.lib.repository.BooksRepository;

import java.time.LocalDateTime;

@Service
public class BorrowService {
    private final BooksRepository booksRepository;

    public BorrowService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void setBorrower(int bookId, int borrowerId) {
        if (borrowerId == -1) {
            turnBookIn(bookId);
        } else {
            borrowBook(bookId, borrowerId);
        }
    }

    private void turnBookIn(int bookId) {
        booksRepository.updateBorrower(bookId, null, null);
    }

    private void borrowBook(int bookId, int borrowerId) {
        Person borrower = new Person();
        borrower.setId(borrowerId);
        booksRepository.updateBorrower(bookId, borrower, LocalDateTime.now());
    }
}
