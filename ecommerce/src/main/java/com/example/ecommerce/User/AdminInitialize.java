package com.example.ecommerce.User;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitialize {
    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initialize() {
        if(!userRepository.existsByRole(Role.ADMIN)) {
            UserModel admin = new UserModel();

            admin.setLogin("neto");
            admin.setPassword(passwordEncoder.encode("12345678"));
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
            System.out.println("Admin "+admin+" created sussecessfully");
        } else {
            System.out.println("Admin already exists");
        }


    }

}
