package ru.turbogoose.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String calculate(@RequestParam int a, @RequestParam int b, @RequestParam String op, Model model) {
        String opSign;
        double result;
        switch (op) {
            case "add" -> {
                opSign = "+";
                result = a + b;
            }
            case "sub" -> {
                opSign = "-";
                result = a - b;
            }
            case "mul" -> {
                opSign = "*";
                result = a * b;
            }
            case "div" -> {
                opSign = "/";
                result = (double) a / b;
            }
            default -> throw new IllegalArgumentException();
        }

        String expression = a + " " + opSign + " " + b + " = " + result;
        model.addAttribute("expr", expression);
        return "calculator/calculator";
    }
}
