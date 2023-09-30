package ru.turbogoose.jwt.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.turbogoose.jwt.models.Person;
import ru.turbogoose.jwt.security.PersonDetails;
import ru.turbogoose.jwt.services.AdminService;

@Controller
public class SecurityController {
    private final AdminService adminService;

    public SecurityController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/user")
    public String showUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        model.addAttribute("user", person.toString());
        return "user";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        adminService.doAdminStuff();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        model.addAttribute("name", person.getName());
        return "admin";
    }
}
