package ru.turbogoose.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.turbogoose.exceptions.PersonNotFoundException;
import ru.turbogoose.exceptions.PersonValidationException;
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
    public Person getPersonById(@PathVariable int id) {
        return peopleService.findById(id);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handle(PersonNotFoundException exc) {
        PersonErrorResponse response = new PersonErrorResponse(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Person createPerson(@RequestBody @Valid Person person,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                sb.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new PersonValidationException(sb.toString());
        }
        return peopleService.save(person);
    }

    @ExceptionHandler(PersonValidationException.class)
    private ResponseEntity<PersonErrorResponse> handlePersonCreationException(PersonValidationException exc) {
        PersonErrorResponse response = new PersonErrorResponse(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
