package com.example.task312.controllers;


import com.example.task312.model.User;
import com.example.task312.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UsersControllers {

    private final UserService userService;

    public UsersControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String start() {
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String showUsers(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "show";
    }

}
