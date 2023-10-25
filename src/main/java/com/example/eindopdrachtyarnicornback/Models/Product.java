package com.example.eindopdrachtyarnicornback.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.util.List;

@Data
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String brand;
    private String color;
    private String blend;
    private Integer needleSize;
    private Integer length;
    private String gauge;
    private String description;
    private String category;
//    private byte[] image;
    // TODO: add image to DTO and Postman entry JSON

//    Relation with Reservation OneToOne. Product is the primary
    @OneToOne
    Reservation reservation;

//    Relation with Profile.
    @ManyToMany(mappedBy = "products")
    List<Profile> profiles;


}
