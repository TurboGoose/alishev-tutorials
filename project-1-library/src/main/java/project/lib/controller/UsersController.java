package project.lib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.lib.dao.PersonDao;
import project.lib.model.Person;

import java.util.List;

@Controller
@RequestMapping("/people")
public class UsersController {
    private final PersonDao dao;

    public UsersController(PersonDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public String getAllPeople(Model model) {
        List<Person> people = dao.getAllPeople();
        model.addAttribute("people", people);
        return "people/list";
    }

    @GetMapping("/new")
    public String getPersonCreationForm(Model model) {
        model.addAttribute("person", new Person());
        return "people/create";
    }

    @PostMapping
    public String createPerson(@ModelAttribute Person person) {
        int id = dao.save(person);
        return "redirect:/people/" + id;
    }
}
