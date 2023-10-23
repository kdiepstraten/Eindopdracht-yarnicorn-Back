package com.example.eindopdrachtyarnicornback.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Reservation {
    @Id
    private Long id;
    private String fullname;
    private String email;
    private String street;
    private Integer streetnumber;
    private String zipcode;
    private String city;
    private Integer amount;
    private String comment;

//    Relation with Products.
    @OneToOne(mappedBy = "reservation")
    Product product;

//    Relation with Users.
    @ManyToOne
    User user;

//    Relation with Profile.
    @ManyToOne
    Profile profile;
}
