package project.lib.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Book> getAllBooks(Integer pageNum, Integer pageSize, String sortField) {
        Sort sort = defineSort(sortField);

        if (pageNum != null && pageSize != null) {
            Pageable pageable;
            if (sort != null) {
                pageable = PageRequest.of(pageNum, pageSize, sort);
            } else {
                pageable = PageRequest.of(pageNum, pageSize);
            }
            return booksRepository.findAll(pageable).getContent();
        }

        if (sort != null) {
            return booksRepository.findAll(sort);
        }

        return booksRepository.findAll();
    }

    private Sort defineSort(String sortField) {
        if (sortField == null) {
            return null;
        }
        return switch (sortField) {
            case "year" -> Sort.by("yearOfPublication");
            default -> null;
        };
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
