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


    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.EAGER)
    private List<Reservation> reservations;


    // One-to-One relationship with FileDocument
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_document_id")
    private FileDocument fileDocument;

}
