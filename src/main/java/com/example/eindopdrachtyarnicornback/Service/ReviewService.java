package com.example.eindopdrachtyarnicornback.Service;

import com.example.eindopdrachtyarnicornback.DTO.ReviewDto;
import com.example.eindopdrachtyarnicornback.Models.Review;
import com.example.eindopdrachtyarnicornback.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewDto> getAllReviews() {
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
        rDto.setFullName(r.getFullName());
    }

    private static void reviewDtoToReview(ReviewDto rDto, Review r) {
        r.setReview(rDto.getReview());
        r.setFullName(rDto.getFullName());
    }

    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = new Review();
        reviewDtoToReview(reviewDto, review);

        Review savedReview = reviewRepository.save(review);

        ReviewDto savedReviewDto = new ReviewDto();
        reviewToReviewDto(savedReview, savedReviewDto);

        return savedReviewDto;
    }
}
