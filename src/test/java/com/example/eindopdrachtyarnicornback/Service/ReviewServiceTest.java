package com.example.eindopdrachtyarnicornback.Service;
import com.example.eindopdrachtyarnicornback.DTO.ReviewDto;
import com.example.eindopdrachtyarnicornback.Models.Review;
import com.example.eindopdrachtyarnicornback.Repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    ReviewService reviewService;

    @Test
    void getAllReviews() {

        //Arrange
        Review review = new Review();
        Review review2 = new Review();

        review.setFullName("Carlos Garcia");
        review.setReview("Toppie");

        review2.setFullName("Taylor Zakhar Perez");
        review2.setReview("Love it!! Here is my number 45345543");

        List<Review> reviews = Arrays.asList(review, review2);

        Mockito.when(reviewRepository.findAll()).thenReturn(reviews);

        //Act
        List<ReviewDto> result = reviewService.getAllReviews();


        //Assert
        assertEquals(2, result.size());
    }

    @Test
    void createReview() {
        
        //Arrange
        ReviewDto newReviewDto = new ReviewDto();
        newReviewDto.setFullName("Alex Clearmont Diaz");
        newReviewDto.setReview("10/10");

        Review newReview = new Review();
        newReview.setFullName(newReviewDto.getFullName());
        newReview.setReview(newReviewDto.getReview());

        Mockito.when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(newReview);

        //Act

        ReviewDto nrDTO = reviewService.createReview(newReviewDto);

        //Assert

        assertEquals("Alex Clearmont Diaz", nrDTO.getFullName());
        assertEquals("10/10", nrDTO.getReview());

    }
}