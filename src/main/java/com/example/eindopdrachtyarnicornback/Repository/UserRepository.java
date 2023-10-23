package com.example.eindopdrachtyarnicornback.Repository;

import com.example.eindopdrachtyarnicornback.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
