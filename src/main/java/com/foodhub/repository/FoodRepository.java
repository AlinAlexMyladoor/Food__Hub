package com.foodhub.repository;

import com.foodhub.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface handles all Database actions (Save, Delete, Find)
public interface FoodRepository extends JpaRepository<FoodItem, Long> {
}