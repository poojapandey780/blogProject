package com.pooja.blogProject.controller;

import com.pooja.blogProject.dto.BlogCardDto;
import com.pooja.blogProject.model.UserPrincipal;
import com.pooja.blogProject.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/home")
public class HomeController {

    @Autowired
    public BlogService blogService;


    @GetMapping
    public String home(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Model model
    ) {
        Page<BlogCardDto> blogPage =
                blogService.getBlogsOfCurrentUser(userPrincipal.getUser().getId(), 0);
        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("currentPage", blogPage.getNumber());
        model.addAttribute("totalPages", blogPage.getTotalPages());
        return "home";
    }



    @GetMapping("/blogs")
    public String loadBlogsAjax(
            @RequestParam int page,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            Model model
    ) {
        Page<BlogCardDto> blogPage =
                blogService.getBlogsOfCurrentUser(
                        userPrincipal.getUser().getId(), page);

        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("currentPage", blogPage.getNumber());
        model.addAttribute("totalPages", blogPage.getTotalPages());

        return "fragments/blog-cards :: blog-cards";
    }



}
