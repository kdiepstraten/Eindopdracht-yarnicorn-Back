package com.example.eindopdrachtyarnicornback.Service;

import com.example.eindopdrachtyarnicornback.DTO.ProductDto;
import com.example.eindopdrachtyarnicornback.Exceptions.IdNotFoundException;
import com.example.eindopdrachtyarnicornback.Models.Product;
import com.example.eindopdrachtyarnicornback.Models.Review;
import com.example.eindopdrachtyarnicornback.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.method.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;
    @Test
    void getAllProducts() {
        Product product1 = new Product();
        product1.setName("Hades");
        product1.setBrand("Greek");
        product1.setColor("Onyx");
        product1.setBlend("50 alpaca 50 wool");
        product1.setNeedleSize(4);
        product1.setLength(50);
        product1.setGauge("10x10=23stx22rows");
        product1.setDescription("Beautiful wool where Hades can be proud off");
        product1.setCategory("Alpaca");

        Product product2 = new Product();
        product2.setName("Aphrodite");
        product2.setBrand("Greek");
        product2.setColor("Rose");
        product2.setBlend("50 alpaca 50 wool");
        product2.setNeedleSize(5);
        product2.setLength(55);
        product2.setGauge("10x10=23stx23rows");
        product2.setDescription("Beautiful wool where Aphrodite can be proud off");
        product2.setCategory("Wool");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Mockito.when(productRepository.findAll()).thenReturn(products);

        List<ProductDto> pdto = productService.getAllProducts();

        assertEquals(2, pdto.size());
    }

    @Test
    void getProduct() {
        Long productId = 1L;
        Product product1 = new Product();
        product1.setName("Apollo");
        product1.setBrand("Greek");
        product1.setColor("Golden");
        product1.setBlend("10 cashmere 40 alpaca 50 wool");
        product1.setNeedleSize(6);
        product1.setLength(100);
        product1.setGauge("10x10=23stx22rows");
        product1.setDescription("Beautiful wool where Apollo can be proud off");
        product1.setCategory("cashmere");

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product1));

        ProductDto productDto = productService.getProduct(productId);

        assertEquals("Apollo", productDto.getName());
        assertEquals("Greek", productDto.getBrand());
        assertEquals("Golden", productDto.getColor());
        assertEquals("10 cashmere 40 alpaca 50 wool", productDto.getBlend());
        assertEquals(6, productDto.getNeedleSize());
        assertEquals(100, productDto.getLength());
        assertEquals("10x10=23stx22rows", productDto.getGauge());
        assertEquals("Beautiful wool where Apollo can be proud off", productDto.getDescription());
        assertEquals("cashmere", productDto.getCategory());
    }
    @Test
    void testGetProductNotFound() {
        // Arrange
        Long productId = 5L; // Replace with an ID that does not exist

        // Configure the mock to return an empty optional when findById is called
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IdNotFoundException.class, () -> productService.getProduct(productId));
    }
    @Test
    void createProduct() {
        ProductDto newProductDTO = new ProductDto();
        newProductDTO.setName("Hades");
        newProductDTO.setBrand("Greek");
        newProductDTO.setColor("Onyx");
        newProductDTO.setBlend("50 alpaca 50 wool");
        newProductDTO.setNeedleSize(4);
        newProductDTO.setLength(50);
        newProductDTO.setGauge("10x10=23stx22rows");
        newProductDTO.setDescription("Beautiful wool where Hades can be proud off");
        newProductDTO.setCategory("Alpaca");

        Product product = new Product();
        product.setName(newProductDTO.getName());
        product.setBrand(newProductDTO.getBrand());
        product.setColor(newProductDTO.getColor());
        product.setBlend(newProductDTO.getBlend());
        product.setNeedleSize(newProductDTO.getNeedleSize());
        product.setLength(newProductDTO.getLength());
        product.setGauge(newProductDTO.getGauge());
        product.setDescription(newProductDTO.getDescription());
        product.setCategory(newProductDTO.getCategory());

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ProductDto productDto = productService.createProduct(newProductDTO);

        assertEquals("Hades", productDto.getName());
        assertEquals("Greek", productDto.getBrand());
        assertEquals("Onyx", productDto.getColor());
        assertEquals("50 alpaca 50 wool", productDto.getBlend());
        assertEquals(4, productDto.getNeedleSize());
        assertEquals(50, productDto.getLength());
        assertEquals("10x10=23stx22rows", productDto.getGauge());
        assertEquals("Beautiful wool where Hades can be proud off", productDto.getDescription());
        assertEquals("Alpaca", productDto.getCategory());
    }

    @Test
    void deleteProduct() {

        // Arrange
        Long productId = 1L; // Replace with a valid product ID
        Mockito.doNothing().when(productRepository).deleteById(productId);

        // Act
        String result = productService.deleteProduct(productId);

        // Assert
        assertEquals("Product deleted", result);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(productId);
    }

    @Test
    void getProductsByCategory(){

        // Arrange
        Product product1 = new Product();
        product1.setName("Hades");
        product1.setBrand("Greek");
        product1.setColor("Onyx");
        product1.setBlend("50 alpaca 50 wool");
        product1.setNeedleSize(4);
        product1.setLength(50);
        product1.setGauge("10x10=23stx22rows");
        product1.setDescription("Beautiful wool where Hades can be proud of");
        product1.setCategory("Alpaca");

        Product product2 = new Product();
        product2.setName("Aphrodite");
        product2.setBrand("Greek");
        product2.setColor("Rose");
        product2.setBlend("50 alpaca 50 wool");
        product2.setNeedleSize(5);
        product2.setLength(55);
        product2.setGauge("10x10=23stx23rows");
        product2.setDescription("Beautiful wool where Aphrodite can be proud of");
        product2.setCategory("Wool");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Mockito.when(productRepository.findByCategory("Alpaca")).thenReturn(products);

        // Act
        List<ProductDto> pdto = productService.getProductsByCategory("Alpaca");

        // Assert
        assertEquals(2, pdto.size());

        // Assert for the first product
        ProductDto pdto1 = pdto.get(0);
        assertEquals("Hades", pdto1.getName());
        assertEquals("Greek", pdto1.getBrand());
        assertEquals("Onyx", pdto1.getColor());
        assertEquals("50 alpaca 50 wool", pdto1.getBlend());
        assertEquals(4, pdto1.getNeedleSize());
        assertEquals(50, pdto1.getLength());
        assertEquals("10x10=23stx22rows", pdto1.getGauge());
        assertEquals("Beautiful wool where Hades can be proud of", pdto1.getDescription());
        assertEquals("Alpaca", pdto1.getCategory());

        // Assert for the second product
        ProductDto pdto2 = pdto.get(1);
        assertEquals("Aphrodite", pdto2.getName());
        assertEquals("Greek", pdto2.getBrand());
        assertEquals("Rose", pdto2.getColor());
        assertEquals("50 alpaca 50 wool", pdto2.getBlend());
        assertEquals(5, pdto2.getNeedleSize());
        assertEquals(55, pdto2.getLength());
        assertEquals("10x10=23stx23rows", pdto2.getGauge());
        assertEquals("Beautiful wool where Aphrodite can be proud of", pdto2.getDescription());
        assertEquals("Wool", pdto2.getCategory());
    }


    }

