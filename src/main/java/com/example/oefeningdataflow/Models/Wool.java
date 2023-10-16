package com.example.oefeningdataflow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Wool {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String brand;
    private String color;
    private String blend;
    private Integer needleSize;
    private Integer length;
    private String category;


}
