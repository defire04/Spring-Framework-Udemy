package org.example.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
//        System.out.println("Hello," + name + " " + surname);
        model.addAttribute("message", "Hello," + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/bye")
    public String byePage() {
        return "first/bye";
    }

//  first/calculator?a=10&b=5&action=addition
    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a, @RequestParam("b") int b,
                             @RequestParam("action") String action, Model model) {
        double result;

        switch (action) {
            case "multiplication" -> result = a * b;
            case "division" -> result = (double) a / b;
            case "addition" -> result = a + b;
            case "subtraction" -> result = a - b;
            default -> result = -404;
        }

        model.addAttribute("result", result);

        return "first/calculator";
    }
}
