package com.foodhub.repository;

import com.foodhub.model.WaiterCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaiterCallRepository extends JpaRepository<WaiterCall, Long> {
    List<WaiterCall> findByStatusOrderByCreatedAtAsc(String status);
}
