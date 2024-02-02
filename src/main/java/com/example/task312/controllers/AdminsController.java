package com.example.task312.controllers;


import com.example.task312.model.Role;
import com.example.task312.model.User;
import com.example.task312.service.RoleService;
import com.example.task312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;
    private final RoleService roleService;

    private final PasswordEncoder encoder;

    @Autowired
    public AdminsController(UserService userService, PasswordEncoder encoder, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }


    @GetMapping("/users")
    public String AllUsers(Model model, Principal principal) {
        User admin = userService.getUserByUsername(principal.getName());
        model.addAttribute("adminEmail", admin.getEmail());
        model.addAttribute("adminRoles", admin.getRoleName());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRole());
        return "index";
    }

    @GetMapping("/")
    public String showAd(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user-admin";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user") User user, @RequestParam(value = "role_id") long roleId) {
        Role role = roleService.getRole(roleId);
        System.err.println(role);
        user.setRole(role);
        userService.editUser(user);
        return "redirect:/admin/users";
    }
    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user, Principal principal, BindingResult bindingResult, Model model, @RequestParam(value = "role_id") String roleId) {

        User admin = userService.getUserByUsername(principal.getName());

        model.addAttribute("adminEmail", admin.getEmail());
        model.addAttribute("adminRoles", admin.getRoleName());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("roles", roleService.getAllRole());

        System.err.println("РОЛЬ: " + roleId);
        String[] roles = roleId.split(",");
        for (String s: roles) {
            long idRole = Long.parseLong(s);
            Role role = roleService.getRole(idRole);
            user.setRole(role);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping()
    public String showOneUser(@PathVariable("id")long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }
}
