package project.lib.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.lib.dao.PersonDao;
import project.lib.model.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonDao personDao;

    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person validatedPerson = (Person) target;
        Optional<Person> optionalPerson = personDao.getPersonByFullName(validatedPerson.getFullName());
        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();
            if (validatedPerson.getId() == 0 || validatedPerson.getId() != existingPerson.getId()) {
                errors.rejectValue("fullName", "", "This full name is already taken");
            }
        }
    }
}
