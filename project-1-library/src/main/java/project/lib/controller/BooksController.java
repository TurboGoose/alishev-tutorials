package project.lib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.lib.dao.BookDao;
import project.lib.model.Book;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDao bookDao;

    public BooksController(BookDao bookDao) {
        this.bookDao = bookDao;
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
}
