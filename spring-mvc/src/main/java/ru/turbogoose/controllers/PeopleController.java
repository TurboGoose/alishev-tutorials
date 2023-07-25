package ru.turbogoose.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.turbogoose.dao.PersonDao;
import ru.turbogoose.models.Person;
import ru.turbogoose.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao dao;
    private final PersonValidator personValidator;

    public PeopleController(PersonDao dao, PersonValidator personValidator) {
        this.dao = dao;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String getAllPeople(Model model) {
        model.addAttribute("people", dao.getAllPeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable int id, Model model) {
        Person person = dao.getPersonById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Person with id %d not found", id))
        );
        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String getCreateNewPersonForm(@ModelAttribute Person person) {
        return "people/new";
    }

    @PostMapping
    public String createNewPerson(@ModelAttribute @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        int generatedId = dao.save(person);
        return "redirect:/people/" + generatedId;
    }

    @GetMapping("/{id}/edit")
    public String getEditPersonForm(@PathVariable int id, Model model) {
        Person person = dao.getPersonById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Person with id %d does no exist", id)));
        model.addAttribute("person", person);
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String editPerson(@PathVariable int id, @ModelAttribute @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        dao.update(id, person);
        return "redirect:/people/" + id;
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        dao.delete(id);
        return "redirect:/people";
    }
}
