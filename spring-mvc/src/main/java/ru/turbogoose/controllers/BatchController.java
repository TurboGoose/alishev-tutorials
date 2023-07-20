package ru.turbogoose.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.turbogoose.dao.PersonDao;

@Controller
@RequestMapping("batch")
public class BatchController {
    private final PersonDao dao;

    public BatchController(PersonDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public String index() {
        return "batch/index";
    }

    @GetMapping("/with")
    public String testUpdateWithBatching() {
        long elapsed = dao.updateWithBatching();
        System.out.println("Update with batching elapsed " + elapsed + " ms");
        return "redirect:/batch";
    }

    @GetMapping("/without")
    public String testUpdateWithoutBatching() {
        long elapsed = dao.updateWithoutBatching();
        System.out.println("Update without batching elapsed " + elapsed + " ms");
        return "redirect:/batch";
    }
}
