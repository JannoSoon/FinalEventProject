package com.example.eventwebapplication.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String showHomePage(Model model, @ModelAttribute("message") String message,
                               @ModelAttribute("messageType") String messageType) {
        boolean isloggedIn = false;
        if(messageType.equals("success")) {
            isloggedIn = true;
            model.addAttribute("message", "Hello, ".concat(SecurityContextHolder.getContext().getAuthentication().getName()).concat("!"));

        }
        model.addAttribute("isLoggedIn", isloggedIn);
        return "home";
    }
}

