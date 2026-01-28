package com.pooja.blogProject.controller;

import com.pooja.blogProject.dto.PersonalInfoDto;
import com.pooja.blogProject.dto.UserDto;
import com.pooja.blogProject.model.User;
import com.pooja.blogProject.model.UserPrincipal;
import com.pooja.blogProject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/profile/update")
public class ProfileController {

    private UserService userService;
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/personalInfo")
    @ResponseBody
    public String updatePersonalInfo(@ModelAttribute("updateUser") PersonalInfoDto dto,
                                     Authentication authentication , Model model) {

        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();

        userService.updatePersonalInfo(user, dto);
        // 4️⃣ Add success message
        model.addAttribute("successMessage", "Profile saved successfully!");


        return "success";
    }
}
