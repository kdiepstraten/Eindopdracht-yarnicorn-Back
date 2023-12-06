package com.example.eindopdrachtyarnicornback.Controllers;
import com.example.eindopdrachtyarnicornback.DTO.ProductDto;
import com.example.eindopdrachtyarnicornback.Exceptions.IdNotFoundException;
import com.example.eindopdrachtyarnicornback.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> pdto = productService.getAllProducts();
        return new ResponseEntity<>(pdto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOneProduct(@PathVariable Long id) {
        ProductDto pdto = productService.getProduct(id);
        return new ResponseEntity<>(pdto, HttpStatus.OK);
    }

    @GetMapping("/byCategory")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@RequestParam String category) {
        List<ProductDto> pdto = productService.getProductsByCategory(category);
        return new ResponseEntity<>(pdto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDTO) {
        ProductDto newProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDTO) {
        try {
            ProductDto updatedProduct = productService.updateProduct(id, productDTO);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
