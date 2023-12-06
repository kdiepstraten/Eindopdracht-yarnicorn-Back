package com.example.eindopdrachtyarnicornback.Service;

import com.example.eindopdrachtyarnicornback.DTO.ProductDto;
import com.example.eindopdrachtyarnicornback.Exceptions.IdNotFoundException;
import com.example.eindopdrachtyarnicornback.Models.FileDocument;
import com.example.eindopdrachtyarnicornback.Models.Product;
import com.example.eindopdrachtyarnicornback.Repository.DocFileRepository;
import com.example.eindopdrachtyarnicornback.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final DocFileRepository docFileRepository;

    public ProductService(ProductRepository productRepository, DocFileRepository docFileRepository) {
        this.productRepository = productRepository;
        this.docFileRepository = docFileRepository;
    }

    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product p : products) {
            ProductDto pdto = new ProductDto();
            productToProductDTO(p, pdto);

            productDtos.add(pdto);
        }
        return productDtos;
    }

    public List<ProductDto> getProductsByCategory(String category) {

        List<Product> products = productRepository.findByCategory(category);
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product p : products) {
            ProductDto pdto = new ProductDto();
            productToProductDTO(p, pdto);
            productDtos.add(pdto);
        }

        return productDtos;
    }


    private void productToProductDTO(Product p, ProductDto pdto) {
        pdto.setName(p.getName());
        pdto.setBrand(p.getBrand());
        pdto.setColor(p.getColor());
        pdto.setLength(p.getLength());
        pdto.setBlend(p.getBlend());
        pdto.setNeedleSize(p.getNeedleSize());
        pdto.setGauge(p.getGauge());
        pdto.setDescription(p.getDescription());
        pdto.setCategory(p.getCategory());
        pdto.setId(p.getId());
        pdto.setDocFile(p.getFileDocument().getDocFile());
        pdto.setFileUrl(p.getFileDocument().getFileName());
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

        // Create and set FileDocument
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(productDTO.getFileUrl());
        fileDocument.setDocFile(productDTO.getFileUrl().getBytes());
        fileDocument.setId(productDTO.getId());


        product.setFileDocument(fileDocument);
    }

    public ProductDto getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            ProductDto pdto = new ProductDto();
            productToProductDTO(p, pdto);

            // Set the file URL in the ProductDto (assuming you have a method to get the file URL)
            pdto.setFileUrl(getFileUrlForProduct(p.getId()));

            return (pdto);
        } else {
            throw new IdNotFoundException("Product not found with id: " + id);
        }
    }
    public String getFileUrlForProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IdNotFoundException("Product not found with id: " + productId));
        return getFileUrlFromDocument(product.getFileDocument());
    }
    private String getFileUrlFromDocument(FileDocument fileDocument) {
        // Implement logic to get the file URL from FileDocument
        // For example, you can concatenate the base URL with the file name
        return "baseURL/" + fileDocument.getFileName();
    }

    public ProductDto createProduct(ProductDto productDTO) {
        Product product = new Product();
        productDTOToProduct(productDTO, product);

        Product savedProduct = productRepository.save(product);

        ProductDto savedProductDto = new ProductDto();
        productToProductDTO(savedProduct, savedProductDto);

        // Set the file URL in the created ProductDto
        savedProductDto.setFileUrl(getFileUrlForProduct(savedProduct.getId()));

        return savedProductDto;
    }

    public String deleteProduct(@RequestBody Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new IdNotFoundException("Product not found with id: " + id);
        }
        return "Product deleted";
    }

    public ProductDto updateProduct(Long id, ProductDto productDTO) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            p.setId(id);
            productDTOToProduct(productDTO, p);
            Product savedProduct = productRepository.save(p);
            ProductDto savedProductDto = new ProductDto();
            productToProductDTO(savedProduct, savedProductDto);

            // Set the file URL in the updated ProductDto
            savedProductDto.setFileUrl(getFileUrlForProduct(savedProduct.getId()));

            return savedProductDto;
        } else {
            throw new IdNotFoundException("Product not found with id: " + id);
        }
    }
}
