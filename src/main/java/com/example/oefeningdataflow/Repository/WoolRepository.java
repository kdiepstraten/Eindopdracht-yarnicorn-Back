package com.example.oefeningdataflow.Repository;

import com.example.oefeningdataflow.Models.Wool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WoolRepository extends JpaRepository<Wool, Long> {
//     static Optional<Wool> findByCategory(String category);


}
