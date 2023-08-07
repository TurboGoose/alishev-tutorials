package project.lib.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.lib.model.Book;
import project.lib.model.Person;
import project.lib.service.PeopleService;
import project.lib.util.PersonValidator;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String getAllPeople(Model model) {
        List<Person> people = peopleService.getAllPeopleWithoutBooks();
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
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/create";
        }
        int id = peopleService.savePerson(person);
        return "redirect:/people/" + id;
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable int id, Model model) {
        Optional<Person> optionalPerson = peopleService.getPersonWithBooksById(id);
        if (optionalPerson.isEmpty()) {
            return "redirect:/people";
        }
        model.addAttribute("person", optionalPerson.get());
        return "people/show";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        peopleService.deletePersonById(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getPersonEditForm(@PathVariable int id, Model model) {
        Optional<Person> optionalPerson = peopleService.getPersonWithoutBooksById(id);
        if (optionalPerson.isEmpty()) {
            return "redirect:/people";
        }
        model.addAttribute("person", optionalPerson.get());
        return "people/edit";
    }

    @PutMapping("/{id}")
    public String editPerson(@PathVariable int id,
                             @Valid @ModelAttribute Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.updatePerson(person);
        return "redirect:/people/" + id;
    }
}
