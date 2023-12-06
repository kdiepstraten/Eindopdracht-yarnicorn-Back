package com.example.eindopdrachtyarnicornback.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FileDocument {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String fileName;
    @Lob
    private byte[] docFile;

    // One-to-One relationship with Product
    @OneToOne ()
    @JoinColumn(name = "product_id")  // Specify the column name in the FileDocument table
    private Product product;

}