package com.foodhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.foodhub.model.FoodItem;
import com.foodhub.repository.FoodRepository;

@SpringBootApplication
public class FoodHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodHubApplication.class, args);
    }

    @Bean
CommandLineRunner initFoodDatabase(FoodRepository repository) {
    return args -> {
        FoodItem f1 = new FoodItem();
        f1.setName("Margherita Pizza");
        f1.setCategory("Main");
        f1.setPrice(12.99);
        repository.save(f1);
    };
}
    
}
