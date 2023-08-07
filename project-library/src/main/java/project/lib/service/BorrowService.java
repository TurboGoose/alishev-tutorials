package project.lib.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.lib.repository.BooksRepository;

@Service
public class BorrowService {
    private final BooksRepository booksRepository;

    public BorrowService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Transactional
    public void turnBookIn(int bookId) {
        booksRepository.updateBorrower(bookId, null);
    }

    @Transactional
    public void borrowBook(int bookId, int borrowerId) {
        booksRepository.updateBorrower(bookId, borrowerId);
    }
}
