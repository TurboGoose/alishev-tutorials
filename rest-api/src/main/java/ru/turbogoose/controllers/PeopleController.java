package ru.turbogoose.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.turbogoose.exceptions.PersonNotFoundException;
import ru.turbogoose.model.Person;
import ru.turbogoose.model.PersonErrorResponse;
import ru.turbogoose.service.PeopleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @GetMapping
    public List<Person> getPeople() {
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable int id) {
        return peopleService.findById(id);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handle(PersonNotFoundException exc) {
        PersonErrorResponse response = new PersonErrorResponse(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
