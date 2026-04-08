package com.foodhub.controller;

import com.foodhub.model.FoodItem;
import com.foodhub.model.Order;
import com.foodhub.model.OrderItem;
import com.foodhub.repository.FoodItemRepository;
import com.foodhub.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/food-items")
    public List<FoodItem> getAvailableFoodItems() {
        return foodItemRepository.findAll().stream().filter(FoodItem::isAvailable).toList();
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        order.setTableNumber(orderRequest.getTableNumber());
        order.setStatus("PLACED");
        
        double total = 0;
        
        for (OrderItemRequest itemReq : orderRequest.getItems()) {
            FoodItem foodItem = foodItemRepository.findById(itemReq.getFoodItemId()).orElse(null);
            if (foodItem != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setFoodItem(foodItem);
                orderItem.setQuantity(itemReq.getQuantity());
                orderItem.setPrice(foodItem.getPrice()); // Lock in the price
                
                order.getItems().add(orderItem);
                total += (foodItem.getPrice() * itemReq.getQuantity());
            }
        }
        
        order.setTotalAmount(total);
        Order savedOrder = orderRepository.save(order);
        
        return ResponseEntity.ok(savedOrder);
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

        public Long getFoodItemId() { return foodItemId; }
        public void setFoodItemId(Long foodItemId) { this.foodItemId = foodItemId; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }
}
