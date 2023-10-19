package com.example.oefeningdataflow.Repository;

import com.example.oefeningdataflow.Models.Role;
import com.example.oefeningdataflow.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
