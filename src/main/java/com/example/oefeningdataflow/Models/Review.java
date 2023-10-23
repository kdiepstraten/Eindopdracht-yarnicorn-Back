package com.example.oefeningdataflow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String fullname;
    private String review;

//    Relation with User.
    @ManyToOne
    User user;
}
