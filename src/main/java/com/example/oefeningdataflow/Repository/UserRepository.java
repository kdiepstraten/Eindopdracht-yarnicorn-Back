package com.example.oefeningdataflow.Repository;

import com.example.oefeningdataflow.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
