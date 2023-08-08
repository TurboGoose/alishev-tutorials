package project.lib.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.lib.model.Book;
import project.lib.model.Person;
import project.lib.service.BooksService;
import project.lib.service.BorrowService;
import project.lib.service.PeopleService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final BorrowService borrowService;
    private final PeopleService peopleService;

    public BooksController(BooksService booksService, BorrowService borrowService, PeopleService peopleService) {
        this.booksService = booksService;
        this.borrowService = borrowService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String getAllBooks(Model model,
                              @RequestParam(required = false) Integer page,
                              @RequestParam(required = false) Integer size,
                              @RequestParam(required = false) String sort) {
        model.addAttribute("books", booksService.getAllBooks(page, size, sort));
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
        int id = booksService.saveBook(book);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable int id, Model model) {
        Optional<Book> optionalBook = booksService.getBookById(id);
        if (optionalBook.isEmpty()) {
            return "redirect:/books";
        }
        Book book = optionalBook.get();
        model.addAttribute("book", book);
        Person borrower = book.getBorrower();
        boolean isBookAvailable = borrower == null;
        model.addAttribute("isBookAvailable", isBookAvailable);
        if (isBookAvailable) {
            model.addAttribute("people", peopleService.getAllPeopleWithoutBooks());
        } else {
            model.addAttribute("person", borrower);
        }
        return "books/show";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        booksService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getEditBookForm(@PathVariable int id, Model model) {
        Optional<Book> optionalBook = booksService.getBookById(id);
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
        book.setId(id);
        booksService.updateBook(book);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}")
    public String setBorrower(@PathVariable("id") int bookId, @RequestParam int borrowerId) {
        borrowService.setBorrower(bookId, borrowerId);
        return "redirect:/books/{id}";
    }

    @GetMapping("/search")
    public String searchBook(Model model, @RequestParam(required = false) String prefix) {
        if (prefix != null) {
            List<Book> matchedBooks = booksService.getBooksByTitle(prefix);
            model.addAttribute("books", matchedBooks);
        }
        return "books/search";
    }
}
