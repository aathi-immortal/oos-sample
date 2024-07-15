package com.aathi.webapp.controller;

import com.aathi.webapp.model.AppUser;
import com.aathi.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/reg")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/reg")
    public String registerUser(AppUser user) {
        userService.createUser(user);
        return "redirect:/user/reg?success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(String username, String password, Model model) {
        if (userService.validateUser(username, password)) {
            return "home";
        } else {
            model.addAttribute("loginError", true);
            return "login";
        }
    }
}
