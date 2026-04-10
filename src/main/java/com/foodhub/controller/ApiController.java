package com.foodhub.controller;

import com.foodhub.model.FoodItem;
import com.foodhub.model.Order;
import com.foodhub.model.OrderItem;
import com.foodhub.model.User;
import com.foodhub.model.WaiterCall;
import com.foodhub.repository.FoodItemRepository;
import com.foodhub.repository.OrderRepository;
import com.foodhub.repository.WaiterCallRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WaiterCallRepository waiterCallRepository;

    @Autowired
    private com.foodhub.repository.FeedbackRepository feedbackRepository;

    @Autowired
    private com.foodhub.repository.CategoryRepository categoryRepository;

@GetMapping("/categories")
    public List<com.foodhub.model.Category> getAllCategories() {
        return categoryRepository.findAll();
    }

@GetMapping("/food-items")
public List<FoodItem> getAvailableFoodItems() {
    // Adding a timestamp or ensuring no-cache helps reflection
    return foodItemRepository.findAllByOrderByNameAsc()
            .stream()
            .collect(java.util.stream.Collectors.toMap(
                    f -> f.getName() == null ? "" : f.getName().trim().toLowerCase(),
                    f -> f,
                    (first, second) -> first,
                    LinkedHashMap::new
            ))
            .values()
            .stream()
            .toList();
}

    @PostMapping("/feedback/submit")
    public ResponseEntity<?> submitFeedback(@RequestBody com.foodhub.model.Feedback feedback) {
        try {
            if (feedback.getCreatedAt() == null) {
                feedback.setCreatedAt(java.time.LocalDateTime.now());
            }
            if (feedback.getRating() < 1 || feedback.getRating() > 5) {
                feedback.setRating(5);
            }
            com.foodhub.model.Feedback saved = feedbackRepository.save(feedback);
            System.out.println("Feedback saved: " + saved.getId() + " - " + saved.getCustomerName() + " - Rating: " + saved.getRating());
            return ResponseEntity.ok(Map.of("success", true, "message", "Thank you for your feedback!", "id", saved.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("success", false, "message", "Error saving feedback: " + e.getMessage()));
        }
    }

    @GetMapping("/feedback/latest")
    public List<com.foodhub.model.Feedback> getLatestFeedbacks() {
        List<com.foodhub.model.Feedback> feedbacks = feedbackRepository.findAllByOrderByCreatedAtDesc();
        System.out.println("Fetching feedbacks. Count: " + feedbacks.size());
        feedbacks.forEach(fb -> System.out.println("- " + fb.getCustomerName() + ": " + fb.getRating() + " stars"));
        return feedbacks;
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest, HttpSession session) {
        Order order = new Order();
        order.setTableNumber(orderRequest.getTableNumber());
        order.setStatus("PLACED");
        
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            order.setCustomerUsername(loggedInUser.getUsername());
        }
        
        double total = 0;
        
        for (OrderItemRequest itemReq : orderRequest.getItems()) {
            FoodItem foodItem = foodItemRepository.findById(itemReq.getFoodItemId()).orElse(null);
            if (foodItem != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setFoodItem(foodItem);
                orderItem.setQuantity(itemReq.getQuantity());
                orderItem.setPrice(foodItem.getPrice()); // Lock in the price
                
                // Set special cooking instructions if provided
                if(itemReq.getSpecialInstructions() != null && !itemReq.getSpecialInstructions().trim().isEmpty()) {
                    orderItem.setSpecialInstructions(itemReq.getSpecialInstructions().trim());
                }
                
                order.getItems().add(orderItem);
                total += (foodItem.getPrice() * itemReq.getQuantity());
            }
        }
        
        order.setTotalAmount(total);
        Order savedOrder = orderRepository.save(order);
        
        return ResponseEntity.ok(savedOrder);
    }
    
    @GetMapping("/orders/my-active")
    public List<Order> getMyActiveOrders(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return new ArrayList<>(); // Empty list if guest via direct API
        
        // Active orders are PLACED, COOKING, READY, SERVED. (We stop tracking them after SERVED feedback, or PAID in admin)
        return orderRepository.findByCustomerUsernameAndStatusIn(
            loggedInUser.getUsername(), 
            List.of("PLACED", "COOKING", "READY", "SERVED")
        );
    }

    @GetMapping("/orders/active")
    public List<Order> getActiveOrders() {
        return orderRepository.findByStatusIn(List.of("PLACED", "COOKING"));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/orders/ready")
    public List<Order> getReadyOrders() {
        return orderRepository.findByStatusIn(List.of("READY"));
    }

    @PostMapping("/orders/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String newStatus = payload.get("status");
        return orderRepository.findById(id).map(order -> {
            order.setStatus(newStatus);
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        }).orElse(ResponseEntity.notFound().build());
    }

    // WAITER CALL ENDPOINTS
    @PostMapping("/waiter/call")
    public ResponseEntity<?> callWaiter(@RequestBody Map<String, Integer> payload) {
        Integer tableNumber = payload.get("tableNumber");
        if (tableNumber == null) return ResponseEntity.badRequest().build();
        
        boolean alreadyPending = waiterCallRepository.findByStatusOrderByCreatedAtAsc("PENDING")
            .stream().anyMatch(c -> c.getTableNumber().equals(tableNumber));
        if (alreadyPending) return ResponseEntity.ok(Map.of("message", "Already requested"));

        WaiterCall call = new WaiterCall();
        call.setTableNumber(tableNumber);
        waiterCallRepository.save(call);
        return ResponseEntity.ok(call);
    }

    @GetMapping("/waiter/calls")
    public List<WaiterCall> getActiveWaiterCalls() {
        return waiterCallRepository.findByStatusOrderByCreatedAtAsc("PENDING");
    }

    @GetMapping("/waiter/calls/status/{tableNumber}")
    public ResponseEntity<Map<String, Boolean>> checkWaiterCallStatus(@PathVariable Integer tableNumber) {
        boolean pending = waiterCallRepository.findByStatusOrderByCreatedAtAsc("PENDING")
            .stream().anyMatch(c -> c.getTableNumber().equals(tableNumber));
        return ResponseEntity.ok(Map.of("pending", pending));
    }

    @PostMapping("/waiter/calls/{id}/resolve")
    public ResponseEntity<?> resolveWaiterCall(@PathVariable Long id) {
        return waiterCallRepository.findById(id).map(call -> {
            call.setStatus("RESOLVED");
            call.setResolvedAt(java.time.LocalDateTime.now());
            waiterCallRepository.save(call);
            return ResponseEntity.ok(call);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DTOs
    public static class OrderRequest {
        private Integer tableNumber;
        private List<OrderItemRequest> items;

        public Integer getTableNumber() { return tableNumber; }
        public void setTableNumber(Integer tableNumber) { this.tableNumber = tableNumber; }
        public List<OrderItemRequest> getItems() { return items; }
        public void setItems(List<OrderItemRequest> items) { this.items = items; }
    }

    public static class OrderItemRequest {
        private Long foodItemId;
        private Integer quantity;
        private String specialInstructions;

        public Long getFoodItemId() { return foodItemId; }
        public void setFoodItemId(Long foodItemId) { this.foodItemId = foodItemId; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        public String getSpecialInstructions() { return specialInstructions; }
        public void setSpecialInstructions(String specialInstructions) { this.specialInstructions = specialInstructions; }
    }
}
