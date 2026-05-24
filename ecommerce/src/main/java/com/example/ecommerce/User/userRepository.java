package com.example.ecommerce.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface userRepository extends JpaRepository<UserModel, Long> {
    boolean existsByLogin(String login);

    UserDetails findByLogin(String login);

    boolean existsByRole(Role role);
}
