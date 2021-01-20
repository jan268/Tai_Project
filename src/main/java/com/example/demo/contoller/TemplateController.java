package com.example.demo.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("courses")
    public String getCourses() {
        return "courses";
    }
}
