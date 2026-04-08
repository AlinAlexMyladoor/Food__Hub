package com.foodhub.config;

import com.foodhub.model.User;
import com.foodhub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            // Check if the admin already exists to avoid duplicates
            if (repository.findAll().stream().noneMatch(u -> u.getUsername().equals("admin"))) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin"); // In a real app, use BCrypt encryption
                admin.setRole("ADMIN");
                
                repository.save(admin);
                System.out.println(">>> Default Admin created: admin/admin");
            }
        };
    }
}