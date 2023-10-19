package com.example.oefeningdataflow.DTO;

import lombok.Data;

@Data
public class ProfileDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;
}
