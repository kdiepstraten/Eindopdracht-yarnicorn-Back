package com.example.oefeningdataflow.Repository;

import com.example.oefeningdataflow.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
