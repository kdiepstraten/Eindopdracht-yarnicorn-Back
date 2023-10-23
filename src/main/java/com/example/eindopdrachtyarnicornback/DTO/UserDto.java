package com.example.eindopdrachtyarnicornback.DTO;

import lombok.Data;

@Data
public class UserDto {


    private String username;
    private String password;

    private String[] roles;

}
