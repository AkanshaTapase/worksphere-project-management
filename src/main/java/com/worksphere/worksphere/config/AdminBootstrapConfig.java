package com.worksphere.worksphere.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.worksphere.worksphere.entity.AppUser;
import com.worksphere.worksphere.entity.Role;
import com.worksphere.worksphere.repository.AppUserRepository;

@Configuration
public class AdminBootstrapConfig {

    @Bean
    public CommandLineRunner createInitialAdmin (
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.bootstrap-admin.email:}") String adminEmail,
            @Value("${app.bootstrap-admin.password:}") String adminPassword) {

        return args -> {

            if (adminEmail.isBlank() || adminPassword.isBlank()) {
                System.out.println("Initial admin credentials not configured.");
                return;
            }

            String email = adminEmail.trim().toLowerCase();

            if (appUserRepository.existsByEmail(email)) {
                System.out.println("Admin account already exists.");
                return;
            }

            if (adminPassword.length() < 12) {
                throw new IllegalStateException(
                        "Initial admin password must contain at least 12 characters.");
            }

            AppUser admin = new AppUser();
            admin.setEmail(email);
            admin.setPasswordHash(passwordEncoder.encode(adminPassword));
            admin.setRole(Role.ADMIN);
            admin.setEnabled(true);

            appUserRepository.save(admin);

            System.out.println("Initial admin account created successfully.");
        };
    }
}