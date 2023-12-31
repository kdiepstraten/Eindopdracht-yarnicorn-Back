package com.example.eindopdrachtyarnicornback.DTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty (message = "Password cannot be empty")
    private String password;
    private String[] roles;

}
