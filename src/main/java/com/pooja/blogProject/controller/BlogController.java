package com.pooja.blogProject.controller;

import com.pooja.blogProject.dto.BlogDto;
import com.pooja.blogProject.model.User;
import com.pooja.blogProject.service.BlogService;
import com.pooja.blogProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user/blog")
public class BlogController {

    @Autowired
    public UserService userService;

    @Autowired
    private BlogService blogService;


    @GetMapping("/")
    public String showForm(Model model)
    {
        model.addAttribute("blogDto", new BlogDto());
        return "blogForm";
    }


    //USER CHECK TITLE IS VALID OR NOT (for current user)
    @GetMapping("/check-title")
    @ResponseBody
    public Map<String, Boolean> checkTitle(
            @RequestParam String blogTitle,
            Authentication authentication) {

        User currentUser = userService.findByEmail(authentication.getName());

        boolean exists = blogService.existsByTitleAndUser(blogTitle, currentUser);

        Map<String, Boolean> response = new HashMap<>();

        if (exists) {
            response.put("available", false); // already used by this user
        } else {
            response.put("available", true);  //  user can use it
        }

        return response;
    }

    @PostMapping("/create")
    public String createBlog(
            @ModelAttribute("blogDto") BlogDto blogDto,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        User user = userService.findByEmail(authentication.getName());

        //  CHECK TITLE AGAIN (SERVER SIDE)
        boolean titleExists =
                blogService.existsByTitleAndUser(blogDto.getTitle(), user);

        if (titleExists) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    " First select a valid blog title"
            );
            return "redirect:/user/blog/";
        }

        // SAVE BLOG
        blogService.createBlog(blogDto, user);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Blog published successfully!"
        );

        return "redirect:/user/blog/";
    }


}
