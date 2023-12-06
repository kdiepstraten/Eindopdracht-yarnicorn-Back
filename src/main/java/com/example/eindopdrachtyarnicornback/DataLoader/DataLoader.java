package com.example.eindopdrachtyarnicornback.DataLoader;

import com.example.eindopdrachtyarnicornback.Models.FileDocument;
import com.example.eindopdrachtyarnicornback.Models.Product;
import com.example.eindopdrachtyarnicornback.Repository.DocFileRepository;
import com.example.eindopdrachtyarnicornback.Repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class DataLoader {

    private final ProductRepository productRepository;

    private final DocFileRepository filerepository;

    public DataLoader(ProductRepository productRepository, DocFileRepository filerepository) {
        this.productRepository = productRepository;
        this.filerepository = filerepository;
    }

 @EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
    public void loadDataOnStartup() throws IOException {

        String folderPath = "src/main/resources/images/";

        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            String imageName = product.getName() + ".jpg";
            Path imagePath = Paths.get(folderPath, imageName);

            if (Files.exists(imagePath)) {
                byte[] imageBytes = Files.readAllBytes(imagePath);

                // Create FileDocument and associate it with the product
                FileDocument fileDocument = new FileDocument();

                fileDocument.setFileName(imageName);
                fileDocument.setDocFile(imageBytes);
                fileDocument.setProduct(product);
                fileDocument.setId(product.getId());
                filerepository.save(fileDocument);
                product.setFileDocument(fileDocument);

                productRepository.save(product);

            }
        }
    }
}