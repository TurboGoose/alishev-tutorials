package ru.turbogoose.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.turbogoose.services.AdminService;
import ru.turbogoose.models.Person;
import ru.turbogoose.security.PersonDetails;

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
