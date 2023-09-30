package ru.turbogoose.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.turbogoose.repositories.PeopleRepository;
import ru.turbogoose.security.PersonDetails;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return peopleRepository.findPersonByName(name)
                .map(PersonDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with name %s not found", name)));
    }
}
