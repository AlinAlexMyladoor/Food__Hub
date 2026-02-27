package com.foodhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    // For Customers
    @GetMapping("/menu")
    public String showMenu() {
        return "menu"; // Looks for menu.html
    }

    // For Admins
    @GetMapping("/admin-dashboard")
    public String showAdmin() {
        return "admin"; // Looks for admin.html
    }

    // For Kitchen Staff
    @GetMapping("/kitchen-orders")
    public String showKitchen() {
        return "kitchen"; // Looks for kitchen.html
    }

    // For Waiters
    @GetMapping("/waiter-dashboard")
    public String showWaiter() {
        return "waiter"; // Looks for waiter.html
    }
}