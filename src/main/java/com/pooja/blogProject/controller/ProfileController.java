package com.pooja.blogProject.controller;

import com.pooja.blogProject.dto.PersonalInfoDto;
import com.pooja.blogProject.dto.UserDto;
import com.pooja.blogProject.model.User;
import com.pooja.blogProject.model.UserPrincipal;
import com.pooja.blogProject.service.ImgService;
import com.pooja.blogProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user/profile/update")
public class ProfileController {

    private UserService userService;
    public ProfileController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public ImgService imgService;

    @PostMapping("/personalInfo")
    @ResponseBody
    public String updatePersonalInfo(@ModelAttribute("updateUser") PersonalInfoDto dto,
                                     Authentication authentication , Model model) {

        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();

        userService.updatePersonalInfo(user, dto);
        // Add success message
        model.addAttribute("successMessage", "Profile saved successfully!");


        return "success";
    }


//    update profile image
    @PostMapping("/img")
    public String updateImg(@RequestParam("image") MultipartFile image,Authentication authentication) {
        String email= authentication.getName();
        imgService.updateProfileImage(email,image);
        return "profile";
    }
}
