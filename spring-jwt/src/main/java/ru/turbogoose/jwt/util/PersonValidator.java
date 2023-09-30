package ru.turbogoose.jwt.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.turbogoose.jwt.dto.PersonDto;
import ru.turbogoose.jwt.services.PeopleService;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PersonDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonDto personToValidate = (PersonDto) o;
        peopleService.getPersonByName(personToValidate.getName()).ifPresent(
                person -> errors.rejectValue("name", "", "User with such name already present")
        );
    }
}
