package ru.turbogoose.util;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.turbogoose.dao.PersonDao;
import ru.turbogoose.models.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonDao dao;

    public PersonValidator(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person targetPerson = (Person) target;
        Optional<Person> optionalPersonByEmail = dao.getPersonByEmail(targetPerson.getEmail());
        if (optionalPersonByEmail.isPresent()) {
            Person personByEmail = optionalPersonByEmail.get();
            if (targetPerson.getId() == 0 || targetPerson.getId() != personByEmail.getId()) {
                errors.rejectValue("email", "", "This email is already taken");
            }
        }
    }
}
