package com.example.eindopdrachtyarnicornback.Service;

import com.example.eindopdrachtyarnicornback.DTO.ProductDto;
import com.example.eindopdrachtyarnicornback.Exceptions.IdNotFoundException;
import com.example.eindopdrachtyarnicornback.Models.Product;
import com.example.eindopdrachtyarnicornback.Models.Reservation;
import com.example.eindopdrachtyarnicornback.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts(){

        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product p : products) {
            ProductDto pdto = new ProductDto();
            productToProductDTO(p, pdto);

            productDtos.add(pdto);
        }
        return productDtos;
    }

    private static void productToProductDTO(Product p, ProductDto pdto) {
        pdto.setName(p.getName());
        pdto.setBrand(p.getBrand());
        pdto.setColor(p.getColor());
        pdto.setLength(p.getLength());
        pdto.setBlend(p.getBlend());
        pdto.setNeedleSize(p.getNeedleSize());
        pdto.setGauge(p.getGauge());
        pdto.setDescription(p.getDescription());
        pdto.setCategory(p.getCategory());
    }
    private void productDTOToProduct(ProductDto productDTO, Product product) {

        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setColor(productDTO.getColor());
        product.setLength(productDTO.getLength());
        product.setGauge(productDTO.getGauge());
        product.setBlend(productDTO.getBlend());
        product.setNeedleSize(productDTO.getNeedleSize());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
    }
    public ProductDto getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            Product p = product.get();
            ProductDto pdto = new ProductDto();
            productToProductDTO(p, pdto);
            return (pdto);
        } else {
            throw new IdNotFoundException("Product not found with id: " + id);
        }
    }

    public ProductDto createProduct(ProductDto productDTO) {
        Product product = new Product();
        productDTOToProduct(productDTO, product);

//        Reservation reservation = new Reservation();
//        reservation.setProduct(product);
//        reservation.setFullName();


        Product savedProduct = productRepository.save(product);

        ProductDto savedProductDto = new ProductDto();
        productToProductDTO(savedProduct, savedProductDto);

        return savedProductDto;
    }

    public String deleteProduct(@RequestBody Long id){

        productRepository.deleteById(id);
        return "Product deleted";
    }

}
