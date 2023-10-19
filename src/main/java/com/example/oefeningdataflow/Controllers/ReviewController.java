package com.example.oefeningdataflow.Controllers;

import com.example.oefeningdataflow.DTO.ReviewDto;
import com.example.oefeningdataflow.DTO.UserDto;
import com.example.oefeningdataflow.Service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/review")
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> rDto = reviewService.getAllUsers();
        return new ResponseEntity<>(rDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto rdto = reviewService.createUser(reviewDto);
        return new ResponseEntity<>(rdto, HttpStatus.CREATED);
    }
}
