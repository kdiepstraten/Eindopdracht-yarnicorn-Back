package com.example.oefeningdataflow.DTO;

import lombok.Data;

@Data
public class ReservationDto {
    private String fullName;
    private String email;
    private String street;
    private Integer streetNumber;
    private String zipcode;
    private String city;
    private Integer amount;
    private String comment;

}
