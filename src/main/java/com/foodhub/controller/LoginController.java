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

    @PostMapping("/login")
    public String login(@RequestParam String username, 
                        @RequestParam String password, 
                        @RequestParam String role, 
                        HttpSession session,
                        Model model) {
        
        User user = userRepository.findByUsername(username);

        // Validation: User exists AND password matches AND the role matches the clicked card
        if (user != null && 
            user.getPassword().equals(password) && 
            user.getRole().equalsIgnoreCase(role)) {
            
            // Save user to session for security/access control
            session.setAttribute("loggedInUser", user);
            
            return switch (user.getRole().toUpperCase()) {
                case "ADMIN"    -> "redirect:/admin-dashboard";
                case "CUSTOMER" -> "redirect:/menu";
                case "KITCHEN"  -> "redirect:/kitchen-orders";
                case "WAITER"   -> "redirect:/waiter-dashboard";
                default         -> "redirect:/"; 
            };
        }

        // FAILURE CASE
        model.addAttribute("error", "Invalid " + role + " credentials.");
        // We pass this back so the HTML knows which login box to stay on
        model.addAttribute("selectedRole", role); 
        return "login"; 
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return "redirect:/?logout=true";
    }
}