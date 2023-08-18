package ru.turbogoose.springboot.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.turbogoose.springboot.models.Person;
import ru.turbogoose.springboot.services.PeopleService;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person personToValidate = (Person) o;
        peopleService.getPersonByName(personToValidate.getName()).ifPresent(
                person -> errors.rejectValue("name", "", "User with such name already present")
        );
    }
}
