package com.example.oefeningdataflow.Models;

import jakarta.persistence.*;
import lombok.Data;

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
    private String description;
    private String category;

    @OneToOne
    Reservation reservation;

}
