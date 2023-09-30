package ru.turbogoose.jwt.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.turbogoose.jwt.dto.AuthenticationDto;
import ru.turbogoose.jwt.dto.PersonDto;
import ru.turbogoose.jwt.mappers.PersonMapper;
import ru.turbogoose.jwt.security.JwtUtil;
import ru.turbogoose.jwt.services.RegistrationService;
import ru.turbogoose.jwt.util.PersonValidator;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final PersonMapper personMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public Map<String, String> register(@RequestBody @Valid PersonDto personDto,
                           BindingResult bindingResult) {
        personValidator.validate(personDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return Map.of("message", "Invalid format!");
        }
        registrationService.register(personMapper.toModel(personDto));
        return Map.of("jwt-token", jwtUtil.generateToken(personDto.getName()));
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                authenticationDto.getName(), authenticationDto.getPassword());
        try {
            authenticationManager.authenticate(token);
        } catch (BadCredentialsException exc) {
            return Map.of("message", "Incorrect credentials");
        }

        String jwt = jwtUtil.generateToken(authenticationDto.getName());
        return Map.of("jwt-token", jwt);
    }
}
