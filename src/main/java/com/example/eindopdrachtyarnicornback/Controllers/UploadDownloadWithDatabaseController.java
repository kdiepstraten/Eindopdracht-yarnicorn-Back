package com.example.eindopdrachtyarnicornback.Controllers;
import com.example.eindopdrachtyarnicornback.FileUploadResponse.FileUploadResponse;
import com.example.eindopdrachtyarnicornback.Models.FileDocument;
import com.example.eindopdrachtyarnicornback.Models.Product;
import com.example.eindopdrachtyarnicornback.Service.DatabaseService;
import com.example.eindopdrachtyarnicornback.Service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
public class UploadDownloadWithDatabaseController {

    private final DatabaseService databaseService;
    private final ProductService productService;

    public UploadDownloadWithDatabaseController(DatabaseService databaseService, ProductService productService) {
        this.databaseService = databaseService;
        this.productService = productService;
    }

    @PostMapping("single/uploadDB")
    public FileUploadResponse singleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") Long productId
    ) throws IOException {

        FileDocument fileDocument = databaseService.uploadFileDocument(file, productId);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileDocument.getFileName(), url, contentType);
    }

    @GetMapping("/downloadFromDB/{fileName}")
    ResponseEntity<String> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        FileDocument document = databaseService.singleFileDownload(fileName, request);

        String data = Base64.getEncoder().encodeToString(document.getDocFile());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + document.getFileName()).body(data);
    }

}