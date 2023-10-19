package com.example.oefeningdataflow.Models;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String roleName;


//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
}
