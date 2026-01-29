package com.pooja.blogProject.controller;

import com.pooja.blogProject.model.User;
import com.pooja.blogProject.model.UserPrincipal;
import com.pooja.blogProject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalUserAdvice {

    private final UserService userService;

    public GlobalUserAdvice(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addUser(Model model, Authentication authentication) {

        if (authentication != null &&
                authentication.getPrincipal() instanceof UserPrincipal) {

            String email = authentication.getName();

            // 🔥 ALWAYS fetch fresh data
            User freshUser = userService.findByEmail(email);

            model.addAttribute("loggedInUser", freshUser);
        }
    }
}


