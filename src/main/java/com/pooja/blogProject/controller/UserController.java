package com.pooja.blogProject.controller;

import com.pooja.blogProject.Util.OtpGenerator;
import com.pooja.blogProject.dto.AccountInfoDto;
import com.pooja.blogProject.dto.PasswordDto;
import com.pooja.blogProject.dto.PersonalInfoDto;
import com.pooja.blogProject.model.PasswordResetToken;
import com.pooja.blogProject.model.User;
import com.pooja.blogProject.model.UserPrincipal;
import com.pooja.blogProject.service.MailService;
import com.pooja.blogProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/user")
@Controller
public class UserController {

    private UserService userService;
    public UserController(UserService userService)
    {
        this.userService = userService;
    }
     @Autowired
     public MailService mailService;

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
    public String profilePage(Model model, Authentication authentication)
    {

        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();

        PersonalInfoDto dto = new PersonalInfoDto();
        dto.setPenName(user.getPenName());
        dto.setBio(user.getBio());

        AccountInfoDto accountInfoDto = new AccountInfoDto();
        PasswordDto passwordDto = new PasswordDto();

        model.addAttribute("updateUser",dto);
        model.addAttribute("accountInfoDto",accountInfoDto);
        model.addAttribute("passwordDto",passwordDto);
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



//    show forgetpassword page
    @GetMapping("/forgotPassword")
    public String showForgetPage()
    {
        return "forgotPassword";
    }



//
@PostMapping("/forgotPassword")
@ResponseBody
public Map<String, Object> forgotPassword(
        @RequestParam String email, HttpSession session) {
    System.out.println(">>> VERIFY OTP CONTROLLER HIT");


    Map<String, Object> response = new HashMap<>();

    if (!userService.emailExists(email)) {
        response.put("success", false);
        response.put("message", "Email not registered");
        return response;
    }

    userService.sendOtp(email);

    session.setAttribute("resetEmail", email);

    response.put("success", true);
    response.put("message",
            "OTP has been sent.");

    return response;
}


 @PostMapping("/verifyOtp")
@ResponseBody
public Map<String, Object> verifyOtp(@RequestParam String otp, HttpSession session) {

    Map<String, Object> response = new HashMap<>();

    String email = (String) session.getAttribute("resetEmail");
     if (email == null) {
         response.put("success", false);
         response.put("message", "Session expired. Please try again.");
         return response;
     }

     boolean valid = userService.verifyOtp(email, otp);

     if (!valid) {
         response.put("success", false);
         response.put("message", "Invalid or expired OTP");
         return response;
     }

     response.put("success", true);
     response.put("message", "OTP verified");

     return response;
}


//reset password
    @PostMapping("/resetPassword")
    @ResponseBody
    public Map<String, Object> resetPassword(@RequestParam String password, HttpSession session) {

        Map<String, Object> response = new HashMap<>();

        String email = (String) session.getAttribute("resetEmail");
        if (email == null) {
            response.put("success", false);
            response.put("message", "Session expired. Please try again.");
            return response;
        }

        try{
            userService.resetPassword(email,password);
            response.put("success",true);
            response.put("message","Password reset successfully");
        }catch(Exception e)
        {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }






}
