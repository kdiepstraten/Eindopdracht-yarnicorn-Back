package com.example.eindopdrachtyarnicornback.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileDto {
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    @Min(value = 8, message = "Password need to contain at least 8 characters")
    private String password;
    @NotEmpty(message = "Confirm password cannot be empty")
    @Min(value = 8, message = "Password need to contain at least 8 characters")
    private String confirmPassword;
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @Email(message = "This needs to be an email adress")
    private String email;
}
