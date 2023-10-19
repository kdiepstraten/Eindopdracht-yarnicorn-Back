package com.example.oefeningdataflow.Controllers;

import com.example.oefeningdataflow.DTO.ProductDto;
import com.example.oefeningdataflow.Service.ProductService;
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
    public ResponseEntity<List<ProductDto>> getAllWool() {
        List<ProductDto> wdto = productService.getAllWool();
        return new ResponseEntity<>(wdto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOneWool(@PathVariable Long id) {
        ProductDto wdto = productService.getWool(id);
        return new ResponseEntity<>(wdto, HttpStatus.OK);
    }

//    @GetMapping("/products")
//    public ResponseEntity<List<WoolDTO>> getProductsByCategory(@RequestParam("category") String category) {
//        List<WoolDTO> products = WoolService.getProductsByCategory(category);
//
//        if (products.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }


    @PostMapping
    public ResponseEntity<ProductDto> createWool(@RequestBody ProductDto productDTO) {
        ProductDto newWool = productService.createWool(productDTO);
        return new ResponseEntity<>(newWool, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteWool(@PathVariable Long id) {
        productService.deleteWool(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
