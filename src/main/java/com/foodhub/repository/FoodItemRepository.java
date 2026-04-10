package com.foodhub.repository;

import com.foodhub.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findAllByOrderByNameAsc();
    List<FoodItem> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
    boolean existsByNameIgnoreCase(String name);
}
