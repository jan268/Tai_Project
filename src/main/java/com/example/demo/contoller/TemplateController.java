package com.example.demo.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLogin(Principal principal) {
        if (principal==null)
        {
        return "login";
        }else return "redirect:/";
    }

    @GetMapping("courses")
    public String getCourses() {
        return "courses";
    }
}
