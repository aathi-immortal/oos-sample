package com.aathi.webapp.controller;

import com.aathi.webapp.model.AppUser;
import com.aathi.webapp.repo.UserRepo;
import com.aathi.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo; // Inject UserRepo

    @GetMapping("/reg")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", AppUser.getInstance());
        return "register";
    }

    @PostMapping("/reg")
    public String registerUser(AppUser user) {
        userService.createUser(user);
        createUser(user);
        return "redirect:/user/login";
    }

    private void createUser(AppUser u) {
        AppUser user = AppUser.getInstance();
        user.setUsername(u.getUsername());
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

    @GetMapping("/createcreditcard")
    public String createCreditCard(Model model) {
        // Get the logged in user (you can use Spring Security for authentication)
        String username = AppUser.getInstance().getUsername(); // Replace with actual authentication logic
        model.addAttribute("status", "");
        System.out.println(username);
        // Find the user by username
        AppUser user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate a 10-digit token (random number)
        if(user.getCreditCardToken() == null)
        {
        Random random = new Random();
        String creditCardToken = String.format("%010d", random.nextInt(1000000000));
        user.setCreditCardToken(creditCardToken);
            userRepo.save(user);
        }
        else{
            model.addAttribute("status", "you already registered for  credit card");
        }
        model.addAttribute("creditCardToken", user.getCreditCardToken());

        return "creditcard";
    }
}
