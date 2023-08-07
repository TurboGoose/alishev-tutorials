package project.lib.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.lib.model.Person;
import project.lib.service.PeopleService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person validatedPerson = (Person) target;
        Optional<Person> optionalPerson = peopleService.getPersonByFullName(validatedPerson.getFullName());
        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();
            if (validatedPerson.getId() == 0 || validatedPerson.getId() != existingPerson.getId()) {
                errors.rejectValue("fullName", "", "This full name is already taken");
            }
        }
    }
}
