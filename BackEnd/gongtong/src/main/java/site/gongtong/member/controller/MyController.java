package site.gongtong.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/home")
    public String home(Model model) {
//        model.addAllAttributes("pageTitle", "Welcome to My Site");
//        model.addAllAttributes("message", "This is a Thymeleaf example.");
        return "index";
    }
}

