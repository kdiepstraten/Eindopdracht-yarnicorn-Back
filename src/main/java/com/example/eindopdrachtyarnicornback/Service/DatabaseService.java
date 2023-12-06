package com.example.eindopdrachtyarnicornback.Service;
import com.example.eindopdrachtyarnicornback.Exceptions.IdNotFoundException;
import com.example.eindopdrachtyarnicornback.Models.FileDocument;
import com.example.eindopdrachtyarnicornback.Models.Product;
import com.example.eindopdrachtyarnicornback.Repository.DocFileRepository;
import com.example.eindopdrachtyarnicornback.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Service
public class DatabaseService {
   private final DocFileRepository doc;

   private final ProductRepository productRepository;

    public DatabaseService(DocFileRepository doc, ProductRepository productRepository){
        this.doc = doc;
        this.productRepository = productRepository;
    }

    public FileDocument uploadFileDocument(MultipartFile file, Long productId) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IdNotFoundException("Product not found with id: " + productId));

        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(file.getOriginalFilename());
        fileDocument.setDocFile(file.getBytes());
        fileDocument.setProduct(product);

        doc.save(fileDocument);

        // Set the FileDocument in the Product
        product.setFileDocument(fileDocument);

        // Save the Product to update the relationship
        productRepository.save(product);

        return fileDocument;

    }

    public FileDocument singleFileDownload(String fileName, HttpServletRequest request){

        FileDocument document = doc.findByFileName(fileName);

        String mimeType = request.getServletContext().getMimeType(document.getFileName());

        return document;

    }

}