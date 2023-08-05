package ru.turbogoose.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.turbogoose.service.DemonstrationService;

@Controller
@RequestMapping("/demo")
public class DemonstrationController {
    private final DemonstrationService demoService;

    public DemonstrationController(DemonstrationService demoService) {
        this.demoService = demoService;
    }

    @GetMapping
    public String demonstrate() {
        demoService.demonstrateNPlusOneProblem();
        return "demo/demo";
    }
}
