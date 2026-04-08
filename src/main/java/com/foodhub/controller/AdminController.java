package com.foodhub.controller;

import com.foodhub.model.FoodItem;
import com.foodhub.model.User;
import com.foodhub.repository.FoodItemRepository;
import com.foodhub.repository.OrderRepository;
import com.foodhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(name = "search", required = false) String search) {
        List<User> userList;
        if (search != null && !search.isEmpty()) {
            userList = userRepository.findByUsernameContainingIgnoreCase(search);
        } else {
            userList = userRepository.findAll();
        }
        
        model.addAttribute("allUsers", userList);
        model.addAttribute("allFoods", foodItemRepository.findAll());
        model.addAttribute("allOrders", orderRepository.findAllByOrderByCreatedAtDesc());
        
        // Simple dashboard stats
        double totalSales = orderRepository.findAll().stream()
            .filter(o -> !o.getStatus().equals("CANCELLED"))
            .mapToDouble(o -> o.getTotalAmount()).sum();
            
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("totalOrders", orderRepository.count());
        model.addAttribute("totalUsers", userRepository.count());
        
        return "admin";
    }

    // User Management
    @PostMapping("/create-user")
    public String createUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    // Food Management
    @PostMapping("/create-food")
    public String createFood(@ModelAttribute FoodItem food) {
        foodItemRepository.save(food);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete-food/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodItemRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }
}