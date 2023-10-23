package com.example.oefeningdataflow.Service;

import com.example.oefeningdataflow.DTO.ProductDto;
import com.example.oefeningdataflow.Exceptions.IdNotFoundException;
import com.example.oefeningdataflow.Models.Product;
import com.example.oefeningdataflow.Repository.ProductRepository;
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

    public List<ProductDto> getAllWool(){

        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product w : products) {
            ProductDto wdto = new ProductDto();
            woolToWoolDTO(w, wdto);

            productDtos.add(wdto);
        }
        return productDtos;
    }

    private static void woolToWoolDTO(Product w, ProductDto wdto) {
        wdto.setName(w.getName());
        wdto.setBrand(w.getBrand());
        wdto.setColor(w.getColor());
        wdto.setId(w.getId());
        wdto.setLength(w.getLength());
        wdto.setBlend(w.getBlend());
        wdto.setNeedleSize(w.getNeedlesize());
        wdto.setGauge(w.getGauge());
        wdto.setDescription(w.getDescription());
        wdto.setCategory(w.getCategory());
    }
    private void woolDTOToWool(ProductDto productDTO, Product product) {

        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setColor(productDTO.getColor());
        product.setLength(productDTO.getLength());
        product.setGauge(productDTO.getGauge());
        product.setBlend(productDTO.getBlend());
        product.setNeedlesize(productDTO.getNeedleSize());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
    }
    public ProductDto getWool(Long id) {
        Optional<Product> wool = productRepository.findById(id);
        if (wool.isPresent()){
            Product w = wool.get();
            ProductDto wdto = new ProductDto();
            woolToWoolDTO(w, wdto);
            return (wdto);
        } else {
            throw new IdNotFoundException("wool not found with id: " + id);
        }
    }

    public ProductDto createWool(ProductDto productDTO) {
        Product product = new Product();
        woolDTOToWool(productDTO, product);

        Product savedProduct = productRepository.save(product);

        ProductDto savedProductDto = new ProductDto();
        woolToWoolDTO(savedProduct, savedProductDto);

        return savedProductDto;
    }

    public void deleteWool(@RequestBody Long id){

        productRepository.deleteById(id);

    }

//    public static List<WoolDTO> getProductsByCategory(String category) {
//        // Implement the logic to retrieve products by category, e.g., from a repository or data source
//        Optional<Wool> products = WoolRepository.findByCategory(category);
//
//        List<WoolDTO> productDtos = new ArrayList<>();
//
//
//        return productDtos;
//    }


}
