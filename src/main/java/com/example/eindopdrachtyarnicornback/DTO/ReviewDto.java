package com.example.eindopdrachtyarnicornback.DTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    @NotEmpty (message = "Full name cannot be empty")
    private String fullName;
    @NotEmpty (message = "Review cannot be empty")
    private String review;
}
