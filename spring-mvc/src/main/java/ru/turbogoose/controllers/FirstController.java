package ru.turbogoose.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(required = false) String name,
                            @RequestParam(required = false) String surname,
                            Model model) {
        String message = composeMessage("Hello", name, surname);
        model.addAttribute("message", message);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String surname,
                              Model model) {
        String message = composeMessage("Goodbye", name, surname);
        model.addAttribute("message", message);
        return "first/goodbye";
    }

    private String composeMessage(String phrase, String name, String surname) {
        StringBuilder sb = new StringBuilder(phrase);
        if (name != null || surname != null) {
            sb.append(",");
        }
        if (name != null) {
            sb.append(" ").append(name);
        }
        if (surname != null) {
            sb.append(" ").append(surname);
        }
        sb.append("!");
        return sb.toString();
    }
}
