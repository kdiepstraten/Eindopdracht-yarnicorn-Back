package com.example.eindopdrachtyarnicornback.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDto {


    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Brand cannot be empty")
    private String brand;
    @NotEmpty(message = "Color cannot be empty")
    private String color;
    @NotEmpty(message = "Blend cannot be empty")
    private String blend;
    @Min(value = 1, message = "Needle size cannot be smaller then 1")
    @Max(value = 10,message = "Needle size cannot be higher then 10")
    private Integer needlesize;

    @Min(value = 1, message = "Length needs to be at least 1")
    private Integer length;
    @NotEmpty(message = "Gauge cannot be empty")
    private String gauge;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @NotEmpty(message = "Catagory cannot be empty")
    private String category;

}
