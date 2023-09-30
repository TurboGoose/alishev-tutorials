package ru.turbogoose.jwt.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.turbogoose.jwt.dto.PersonDto;
import ru.turbogoose.jwt.models.Person;

@Component
@RequiredArgsConstructor
public class PersonMapper {
    private final ModelMapper modelMapper;

    public PersonDto toDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    public Person toModel(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }
}
