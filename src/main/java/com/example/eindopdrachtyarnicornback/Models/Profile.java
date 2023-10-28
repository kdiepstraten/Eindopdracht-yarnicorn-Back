package com.example.eindopdrachtyarnicornback.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Profile {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;

    //TODO: in DB table users profileID is null.
//    Relation with User OneToOne.
    @OneToOne
    User user;

//    Relation with Reservation OneToMany.
    @OneToMany(mappedBy = "profile")
    List<Reservation> reservations;

//    Relation with Product ManyToMany.
    @ManyToMany
    List<Product> products;

//    Relation with Review OneToMany.
    @OneToMany
    List<Review> review;
}
