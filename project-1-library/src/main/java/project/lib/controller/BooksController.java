package project.lib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.lib.dao.BookDao;

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
}
