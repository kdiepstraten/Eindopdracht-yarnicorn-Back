package com.example.eindopdrachtyarnicornback.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthDTO {
    @NotEmpty (message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Confirm password cannot be empty")
    @Min(value = 8, message = "Password need to contain at least 8 characters")
    private String password;
}
