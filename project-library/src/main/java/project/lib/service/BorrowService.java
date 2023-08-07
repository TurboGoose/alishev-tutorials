package project.lib.service;

import org.springframework.stereotype.Service;
import project.lib.repository.BooksRepository;

@Service
public class BorrowService {
    private final BooksRepository booksRepository;

    public BorrowService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void turnBookIn(int bookId) {
        booksRepository.updateBorrower(bookId, null);
    }

    public void borrowBook(int bookId, int borrowerId) {
        booksRepository.updateBorrower(bookId, borrowerId);
    }
}
