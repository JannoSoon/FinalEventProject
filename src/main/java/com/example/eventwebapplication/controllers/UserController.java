package com.example.eventwebapplication.controllers;

import com.example.eventwebapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showAllUserPage(Model model, @ModelAttribute("message") String message,
                                  @ModelAttribute("messageType") String messageType) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/list-user";
    }
}
