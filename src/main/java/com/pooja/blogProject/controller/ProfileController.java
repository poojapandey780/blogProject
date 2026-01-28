package com.pooja.blogProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/profile/update")
public class ProfileController {


    @PostMapping("/personalInfo")
    public String updatePersonalInfo()
    {
        return "";
    }
}
