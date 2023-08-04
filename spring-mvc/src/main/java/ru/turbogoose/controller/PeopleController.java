package ru.turbogoose.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.turbogoose.model.Person;
import ru.turbogoose.service.PeopleService;
import ru.turbogoose.util.PersonValidator;


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
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable int id, Model model) {
        Person person = peopleService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Person with id %d not found", id))
        );
        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String getCreateNewPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String createNewPerson(@ModelAttribute @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people/" + person.getId();
    }

    @GetMapping("/{id}/edit")
    public String getEditPersonForm(@PathVariable int id, Model model) {
        Person person = peopleService.findById(id).orElseThrow(
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
        person.setId(id);
        peopleService.update(person);
        return "redirect:/people/" + id;
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
