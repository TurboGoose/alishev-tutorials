package ru.turbogoose.util;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.turbogoose.model.Person;
import ru.turbogoose.repository.PeopleRepository;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleRepository peopleRepository;

    public PersonValidator(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person targetPerson = (Person) target;
        Optional<Person> optionalPersonByEmail = peopleRepository.findByEmail(targetPerson.getEmail());
        if (optionalPersonByEmail.isPresent()) {
            Person personByEmail = optionalPersonByEmail.get();
            if (targetPerson.getId() == 0 || targetPerson.getId() != personByEmail.getId()) {
                errors.rejectValue("email", "", "This email is already taken");
            }
        }
    }
}
