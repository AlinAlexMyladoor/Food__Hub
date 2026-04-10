package com.foodhub.controller;

import com.foodhub.model.FoodItem;
import com.foodhub.model.User;
import com.foodhub.model.Category;
import com.foodhub.repository.FoodItemRepository;
import com.foodhub.repository.OrderRepository;
import com.foodhub.repository.UserRepository;
import com.foodhub.repository.CategoryRepository;
import com.foodhub.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @jakarta.annotation.PostConstruct
    public void migrateCategories() {
        if (categoryRepository.count() == 0) {
            foodItemRepository.findAll().stream()
                .map(FoodItem::getCategory)
                .filter(c -> c != null && !c.trim().isEmpty())
                .distinct()
                .forEach(catName -> {
                    Category c = new Category();
                    c.setName(catName.trim());
                    categoryRepository.save(c);
                });
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, 
                            @RequestParam(name = "search", required = false) String search,
                            @RequestParam(name = "menuSearch", required = false) String menuSearch) {
        
        List<User> userList;
        if (search != null && !search.isEmpty()) {
            userList = userRepository.findByUsernameContainingIgnoreCase(search);
        } else {
            userList = userRepository.findAll();
        }
        
        List<FoodItem> foodList;
        if (menuSearch != null && !menuSearch.isEmpty()) {
            foodList = foodItemRepository.findByNameContainingIgnoreCaseOrderByNameAsc(menuSearch);
        } else {
            foodList = foodItemRepository.findAllByOrderByNameAsc();
        }
        foodList = foodList.stream()
            .collect(java.util.stream.Collectors.toMap(
                f -> f.getName() == null ? "" : f.getName().trim().toLowerCase(),
                f -> f,
                (first, second) -> first,
                LinkedHashMap::new
            ))
            .values()
            .stream()
            .toList();

        model.addAttribute("allUsers", userList);
        model.addAttribute("allFoods", foodList);
        model.addAttribute("allOrders", orderRepository.findAllByOrderByCreatedAtDesc());
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allFeedbacks", feedbackRepository.findAllByOrderByCreatedAtDesc());
        
        double totalSales = orderRepository.findAll().stream()
            .filter(o -> !o.getStatus().equals("CANCELLED"))
            .mapToDouble(o -> o.getTotalAmount()).sum();
            
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("totalOrders", orderRepository.count());
        model.addAttribute("totalUsers", userRepository.count());
        
        return "admin";
    }

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

    @PostMapping("/create-food")
    public String createFood(@ModelAttribute FoodItem food) {
        if (food.getName() != null && !food.getName().trim().isEmpty()) {
            if (!foodItemRepository.existsByNameIgnoreCase(food.getName().trim())) {
                food.setName(food.getName().trim());
                foodItemRepository.save(food);
            }
        }
        return "redirect:/admin/dashboard";
    }

    
    @PostMapping("/update-food")
public String updateFood(@ModelAttribute FoodItem food) {
    FoodItem existing = foodItemRepository.findById(food.getId()).orElse(null);
    if (existing != null) {
        existing.setName(food.getName());
        existing.setCategory(food.getCategory());
        existing.setPrice(food.getPrice());
        existing.setImageUrl(food.getImageUrl());
        
        // Explicitly set these booleans
        existing.setVeg(food.isVeg()); 
        existing.setAvailable(food.isAvailable());
        
        foodItemRepository.save(existing);
    }
    return "redirect:/admin/dashboard";
}

    @GetMapping("/delete-food/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodItemRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/create-category")
    public String createCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete-order/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete-feedback/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/get-feedbacks")
    public String getFeedbacks(Model model) {
        model.addAttribute("allFeedbacks", feedbackRepository.findAllByOrderByCreatedAtDesc());
        return "admin";
    }
}