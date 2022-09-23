package com.example.eventwebapplication.controllers;

import com.example.eventwebapplication.exceptions.InvalidLoginException;
import com.example.eventwebapplication.models.User;
import com.example.eventwebapplication.services.AuthorityService;
import com.example.eventwebapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private UserService userService;

    @Autowired
    AuthorityService authorityService;

    @GetMapping()
    public String showSignupPage(@ModelAttribute("user") User user, @ModelAttribute("message") String message,
                                 @ModelAttribute("messageType") String messageType, Model model){
        model.addAttribute("authorities", authorityService.findAuthorities());
        return "auth/signup";
    }
    @PostMapping
    public String postSignup(User user, RedirectAttributes redirectAttributes) {

        try{
            userService.findUserByUserName(user.getUserName());
            redirectAttributes.addFlashAttribute("message", "user already exist!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/signup";
        } catch(InvalidLoginException ive){
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("message", "Signup successful!");
            redirectAttributes.addFlashAttribute("messageType", "succes");
            return "redirect:/";
        }
    }
}

