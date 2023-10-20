package com.example.oefeningdataflow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Reservation {
    @Id
    private Long id;
    private String fullName;
    private String email;
    private String street;
    private Integer streetNumber;
    private String zipcode;
    private String city;
    private Integer amount;
    private String comment;

    @OneToOne(mappedBy = "reservation")
    Product product;
}
