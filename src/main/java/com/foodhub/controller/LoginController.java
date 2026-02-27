package com.foodhub.controller;

import com.foodhub.model.User;
import com.foodhub.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }
    @GetMapping("/logout")
public String logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
        session.invalidate(); // This clears the user's data from memory
    }
    return "redirect:/?logout=true"; // Sends them back to login page
}
    @PostMapping("/login")
public String login(@RequestParam String username, 
                    @RequestParam String password, 
                    Model model) {
    
    User user = userRepository.findByUsername(username);

    if (user != null && user.getPassword().equals(password)) {
        // Successful login
        return switch (user.getRole().toUpperCase()) {
            case "ADMIN" -> "redirect:/admin-dashboard";
            case "CUSTOMER" -> "redirect:/menu";
            case "KITCHEN" -> "redirect:/kitchen-orders";
            case "WAITER" -> "redirect:/waiter-dashboard";
            default -> "redirect:/";
        };
    }

    model.addAttribute("error", "Access Denied: Incorrect Username or Password.");
    return "login";
}
}