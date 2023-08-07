package project.lib.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.lib.dao.BookDao;
import project.lib.dao.PersonDao;
import project.lib.model.Book;
import project.lib.model.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDao bookDao;
    private final PersonDao personDao;

    public BooksController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDao.getAllBooks());
        return "books/list";
    }

    @GetMapping("/new")
    public String getBookCreationForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/create";
    }

    @PostMapping
    public String createBook(@Valid @ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/create";
        }
        int id = bookDao.save(book);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable int id, Model model) {
        Optional<Book> optionalBook = bookDao.getBookById(id);
        if (optionalBook.isEmpty()) {
            return "redirect:/books";
        }
        model.addAttribute("book", optionalBook.get());
        Optional<Person> optionalPerson = personDao.getBorrowerByBookId(id);
        boolean isBookAvailable = optionalPerson.isEmpty();
        model.addAttribute("isBookAvailable", isBookAvailable);
        if (isBookAvailable) {
            model.addAttribute("people", personDao.getAllPeople());
        } else {
            model.addAttribute("person", optionalPerson.get());
        }
        return "books/show";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getEditBookForm(@PathVariable int id, Model model) {
        Optional<Book> optionalBook = bookDao.getBookById(id);
        if (optionalBook.isEmpty()) {
            return "redirect:/books";
        }
        model.addAttribute("book", optionalBook.get());
        return "books/edit";
    }

    @PutMapping("/{id}")
    public String editBook(@PathVariable int id,
                           @Valid @ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDao.update(id, book);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}")
    public String setBorrower(@PathVariable("id") int bookId, @RequestParam int borrowerId) {
        if (borrowerId == -1) {
            bookDao.turnIn(bookId);
        } else {
            bookDao.borrow(bookId, borrowerId);
        }
        return "redirect:/books/{id}";
    }
}
