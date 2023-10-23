package com.example.eindopdrachtyarnicornback.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String rolename;


    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
