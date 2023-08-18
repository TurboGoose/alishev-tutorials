package ru.turbogoose.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.turbogoose.springboot.services.PersonDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;

    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/registration", "/login", "/error").permitAll()
//            .antMatchers("/admin").hasRole("ADMIN")
            .anyRequest().hasAnyRole("USER", "ADMIN")
            .and()
            .formLogin().loginPage("/login")
            .defaultSuccessUrl("/user", true)
            .failureUrl("/login?error")
            .loginProcessingUrl("/process")
            .and()
            .logout().logoutUrl("/logout")
            .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
