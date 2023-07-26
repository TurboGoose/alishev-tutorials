package project.lib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.lib.dao.PersonDao;
import project.lib.model.Person;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao dao;

    public PeopleController(PersonDao dao) {
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
    public String createPerson(@Valid @ModelAttribute Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/create";
        }
        int id = dao.save(person);
        return "redirect:/people/" + id;
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable int id, Model model) {
        Optional<Person> optionalPerson = dao.getPersonById(id);
        if (optionalPerson.isEmpty()) {
            return "redirect:/people";
        }
        model.addAttribute("person", optionalPerson.get());
        return "people/show";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        dao.deleteById(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getPersonEditForm(@PathVariable int id, Model model) {
        Optional<Person> optionalPerson = dao.getPersonById(id);
        if (optionalPerson.isEmpty()) {
            return "redirect:/people";
        }
        model.addAttribute("person", optionalPerson.get());
        return "people/edit";
    }

    @PutMapping("/{id}")
    public String editPerson(@PathVariable int id,
                             @Valid @ModelAttribute Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        dao.updatePerson(id, person);
        return "redirect:/people/" + id;
    }
}
