package com.example.eindopdrachtyarnicornback.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReservationDto {
    @NotEmpty(message = "Full name cannot be empty")
    private String fullName;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "This needs to be an email adress")
    private String email;
    @NotEmpty(message = "Street cannot be empty")
    private String street;
    @NotEmpty(message = "Street number cannot be empty")
    private Integer streetNumber;
    @NotEmpty(message = "Zipcode cannot be empty")
    private String zipcode;
    @NotEmpty(message = "City cannot be empty")
    private String city;
    @NotEmpty(message = "Amount cannot be empty")
    @Min(1)
    @Positive(message = "The amount needs to be higher then zero")
    private Integer amount;
    @NotEmpty(message = "Comment cannot be empty")
    private String comment;

}
