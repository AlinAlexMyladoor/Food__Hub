package com.foodhub.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Tells Spring to save this in the database
@Data   // Automatically generates Getters and Setters
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String category; // Starter, Main, etc.
    private double price;
    private String imageUrl;
}