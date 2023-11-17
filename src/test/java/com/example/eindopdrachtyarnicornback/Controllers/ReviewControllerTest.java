package com.example.eindopdrachtyarnicornback.Controllers;

import com.example.eindopdrachtyarnicornback.DTO.ReviewDto;
import com.example.eindopdrachtyarnicornback.Models.Review;
import com.example.eindopdrachtyarnicornback.Repository.ReviewRepository;
import com.example.eindopdrachtyarnicornback.Security.JwtService;
import com.example.eindopdrachtyarnicornback.Service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ReviewController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class ReviewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReviewService reviewService;

    @MockBean
    ReviewRepository reviewRepository;

    @MockBean
    JwtService jwtService;

    @Test
    void getAllReviews() throws Exception {

        ReviewDto rdto = new ReviewDto();
        rdto.setFullName("Tom Holland");
        rdto.setReview("Jeez what a great site!");

        ReviewDto rdto2 = new ReviewDto();
        rdto2.setFullName("Andrew Garfield");
        rdto2.setReview("I dont get it at all but it sure is pretty");

        List<ReviewDto> reviewDtoList = new ArrayList<>();
        reviewDtoList.add(rdto);
        reviewDtoList.add(rdto2);

        Mockito.when(reviewService.getAllReviews()).thenReturn(reviewDtoList);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/review"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName", is("Tom Holland")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].review", is("Jeez what a great site!")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].fullName", is("Andrew Garfield")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].review", is("I dont get it at all but it sure is pretty")));

    }

    @Test
    void createReview() throws Exception {

        Review review = new Review();
        review.setFullName("Jake");
        review.setReview("This is amazing!!");

        reviewRepository.save(review);

        ReviewDto rdto = new ReviewDto();
        rdto.setFullName(review.getFullName());
        rdto.setReview(review.getReview());

        Mockito.when(reviewService.createReview(Mockito.any(ReviewDto.class))).thenReturn(rdto);


        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/review").content("{\"fullName\" : \"Jake\",\n" +
                        "\t\"review\" : \"This is amazing!!\"}").contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Jake"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.review").value("This is amazing!!"));
    }
}