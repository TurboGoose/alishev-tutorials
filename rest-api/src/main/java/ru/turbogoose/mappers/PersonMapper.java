package ru.turbogoose.mappers;

import org.springframework.stereotype.Component;
import ru.turbogoose.dto.PersonDto;
import ru.turbogoose.model.Person;

@Component
public class PersonMapper {
    public PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setAge(person.getAge());
        dto.setName(person.getName());
        dto.setEmail(person.getEmail());
        return dto;
    }

    public Person toModel(PersonDto personDto) {
        Person model = new Person();
        model.setAge(personDto.getAge());
        model.setName(personDto.getName());
        model.setEmail(personDto.getEmail());
        return model;
    }
}
