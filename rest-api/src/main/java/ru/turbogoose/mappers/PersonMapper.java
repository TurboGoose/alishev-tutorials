package ru.turbogoose.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.turbogoose.dto.PersonRequestDto;
import ru.turbogoose.dto.PersonResponseDto;
import ru.turbogoose.model.Person;

@Component
@RequiredArgsConstructor
public class PersonMapper {
    private final ModelMapper modelMapper;

    public PersonResponseDto toDto(Person person) {
        return modelMapper.map(person, PersonResponseDto.class);
    }

    public Person toModel(PersonRequestDto personRequestDto) {
        return modelMapper.map(personRequestDto, Person.class);
    }
}
