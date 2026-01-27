package com.pooja.blogProject.controller;

import com.pooja.blogProject.model.User;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @PostMapping("/login")
    public String login(@ModelAttribute User user)
    {

        return "home";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
}
