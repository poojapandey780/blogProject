package com.pooja.blogProject.controller;

import com.pooja.blogProject.dto.PersonalInfoDto;
import com.pooja.blogProject.model.User;
import com.pooja.blogProject.model.UserPrincipal;
import com.pooja.blogProject.service.UserService;
import jakarta.persistence.Persistence;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {

    private UserService userService;
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage()
    {
        return "login";
    }

    @PostMapping("/login")
    public String showLoginPage(@ModelAttribute User user) {return "home";}

    // here user object send to form that you can bind, with this object and send data in form of object
    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "register"; }

//    now here we take take
    @PostMapping("/register")
    public String showRegisterForm(@ModelAttribute User user)
    {
        userService.registerUser(user);
        return "redirect:/user/login" ;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

//
    @GetMapping("/profile")
    public String profilePage(Model model)
    {
        PersonalInfoDto dto = new PersonalInfoDto();
        model.addAttribute("updateUser",dto);
        return "profile";
    }


    @GetMapping("/check-penname")
    @ResponseBody
    public Map<String, Boolean> checkPenName(
            @RequestParam String penName,
            Authentication authentication) {

        User currentUser = userService.findByEmail(authentication.getName());

        boolean exists = userService.existsByPenName(penName);

        // Allow same user to keep their own pen name
        boolean available = !exists || penName.equals(currentUser.getPenName());

        return Map.of("available", available);
    }

}
