package ru.turbogoose.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.turbogoose.dto.PersonRequestDto;
import ru.turbogoose.dto.PersonResponseDto;
import ru.turbogoose.exceptions.PersonNotFoundException;
import ru.turbogoose.mappers.PersonMapper;
import ru.turbogoose.model.Person;
import ru.turbogoose.model.PersonErrorResponse;
import ru.turbogoose.service.PeopleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final PersonMapper personMapper;

    @GetMapping
    public List<PersonResponseDto> getPeople() {
        return peopleService.findAll().stream()
                .map(personMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public PersonResponseDto getPersonById(@PathVariable int id) {
        return personMapper.toDto(peopleService.findById(id));
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handle(PersonNotFoundException exc) {
        PersonErrorResponse response = new PersonErrorResponse(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public PersonResponseDto createPerson(@RequestBody @Valid PersonRequestDto personRequestDto) {
        Person person = personMapper.toModel(personRequestDto);
        return personMapper.toDto(peopleService.save(person));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Map<String, String> handlePersonCreationException(MethodArgumentNotValidException exc) {
        Map<String, String> errors = new HashMap<>();
        exc.getBindingResult().getFieldErrors().forEach(
                fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        return errors;
    }
}
