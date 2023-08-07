package project.lib.service;

import org.springframework.stereotype.Service;
import project.lib.model.Book;
import project.lib.repository.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public Optional<Book> getBookById(int id) {
        return booksRepository.findById(id);
    }

    public int saveBook(Book book) {
        booksRepository.save(book);
        return book.getId();
    }

    public void updateBook(Book book) {
        booksRepository.save(book);
    }

    public void deleteBookById(int id) {
        booksRepository.deleteById(id);
    }
}
