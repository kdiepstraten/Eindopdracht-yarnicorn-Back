package com.example.oefeningdataflow.DTO;

import lombok.Data;

@Data
public class ProductDto {

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

}
