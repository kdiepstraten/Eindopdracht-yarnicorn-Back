package com.example.oefeningdataflow.DTO;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDto {


    private String username;
    private String password;

    private String[] roles;

}
