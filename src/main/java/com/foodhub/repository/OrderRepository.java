package com.foodhub.repository;

import com.foodhub.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
    List<Order> findByStatusIn(List<String> statuses);
    List<Order> findByStatusOrderByCreatedAtDesc(String status);
    List<Order> findAllByOrderByCreatedAtDesc();
    
    // To grab active orders for a customer (anything not PAID or SERVED beyond tracking)
    List<Order> findByCustomerUsernameAndStatusIn(String customerUsername, List<String> statuses);
}
