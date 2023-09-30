package ru.turbogoose.jwt.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.turbogoose.jwt.dto.PersonDto;
import ru.turbogoose.jwt.mappers.PersonMapper;
import ru.turbogoose.jwt.security.PersonDetails;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final PersonMapper personMapper;

    @GetMapping("/user")
    public PersonDto showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personMapper.toDto(personDetails.getPerson());
    }
}
