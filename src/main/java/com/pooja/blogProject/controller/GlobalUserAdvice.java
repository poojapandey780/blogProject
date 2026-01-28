package com.pooja.blogProject.controller;

import com.pooja.blogProject.model.User;
import com.pooja.blogProject.model.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalUserAdvice {

    @ModelAttribute
    public void addUser(Model model, Authentication authentication) {

        if (authentication != null &&
                authentication.getPrincipal() instanceof UserPrincipal) {

            User user =
                    ((UserPrincipal) authentication.getPrincipal()).getUser();

            model.addAttribute("loggedInUser", user);
        }
    }
}

