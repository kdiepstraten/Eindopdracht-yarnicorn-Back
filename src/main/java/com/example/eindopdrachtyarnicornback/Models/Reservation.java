package com.example.eindopdrachtyarnicornback.Models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    private String email;
    private String street;
    private Integer streetNumber;
    private String zipcode;
    private String city;
    private Integer amount;
    private String comment;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
