package com.example.eindopdrachtyarnicornback.Models;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "products")
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


    //    Relation with Reservation OneToOne. Product is the primary
    @OneToMany(mappedBy = "product")
    private List<Reservation> reservations;


}
