package ru.turbogoose.mappers;

import org.springframework.stereotype.Component;
import ru.turbogoose.dto.PersonRequestDto;
import ru.turbogoose.dto.PersonResponseDto;
import ru.turbogoose.model.Person;

@Component
public class PersonMapper {
    public PersonResponseDto toDto(Person person) {
        PersonResponseDto dto = new PersonResponseDto();
        dto.setId(person.getId());
        dto.setAge(person.getAge());
        dto.setName(person.getName());
        dto.setEmail(person.getEmail());
        return dto;
    }

    public Person toModel(PersonRequestDto personRequestDto) {
        Person model = new Person();
        model.setAge(personRequestDto.getAge());
        model.setName(personRequestDto.getName());
        model.setEmail(personRequestDto.getEmail());
        return model;
    }
}
