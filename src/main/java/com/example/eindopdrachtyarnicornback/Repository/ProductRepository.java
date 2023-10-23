package com.example.eindopdrachtyarnicornback.Repository;

import com.example.eindopdrachtyarnicornback.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
