package com.foodhub.controller;

import com.foodhub.model.User;
import com.foodhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/admin/create-user")
    public String createUser(@ModelAttribute User newUser) {
        userRepository.save(newUser); // This saves the user to the database permanently
        return "redirect:/admin-dashboard?success=UserCreated";
    }
    
}