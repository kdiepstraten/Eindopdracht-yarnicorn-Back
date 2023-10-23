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
    private String confirmpassword;
    private String firstname;
    private String lastname;
    private String email;

//    Relation with User
    @OneToOne(mappedBy = "profile")
    User user;

//    Relation with Reservation.
    @OneToMany(mappedBy = "profile")
    List<Reservation> reservations;

}
