package com.foodhub.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "waiter_calls")
@Data
public class WaiterCall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tableNumber;

    // Status can be: PENDING, RESOLVED
    private String status = "PENDING";

    private LocalDateTime createdAt = LocalDateTime.now();
    
    private LocalDateTime resolvedAt;
}
