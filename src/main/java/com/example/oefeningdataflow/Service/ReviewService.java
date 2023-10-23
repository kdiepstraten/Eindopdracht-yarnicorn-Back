package com.example.oefeningdataflow.Service;

import com.example.oefeningdataflow.DTO.ReviewDto;
import com.example.oefeningdataflow.Models.Review;
import com.example.oefeningdataflow.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewDto> getAllUsers() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDto> reviewDtos = new ArrayList<>();

        for (Review r : reviews) {
            ReviewDto rDto = new ReviewDto();
            reviewToReviewDto(r, rDto);

            reviewDtos.add(rDto);
        }
        return reviewDtos;
    }

    private static void reviewToReviewDto(Review r, ReviewDto rDto) {
        rDto.setReview(r.getReview());
        rDto.setFullName(r.getFullname());
    }

    private static void reviewDtoToReview(ReviewDto rDto, Review r) {
        r.setReview(rDto.getReview());
        r.setFullname(rDto.getFullName());
    }

    public ReviewDto createUser(ReviewDto reviewDto) {
        Review review = new Review();
        reviewDtoToReview(reviewDto, review);

        Review savedReview = reviewRepository.save(review);

        ReviewDto savedReviewDto = new ReviewDto();
        reviewToReviewDto(savedReview, savedReviewDto);

        return savedReviewDto;
    }
}
