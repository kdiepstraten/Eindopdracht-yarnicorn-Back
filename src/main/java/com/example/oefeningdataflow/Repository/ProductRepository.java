package com.example.oefeningdataflow.Repository;

import com.example.oefeningdataflow.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
