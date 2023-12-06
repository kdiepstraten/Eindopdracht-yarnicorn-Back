package com.example.eindopdrachtyarnicornback.Base64;

import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class ImageToBase64 {
    public static void main(String[] args) throws Exception {
        String imagePath = "C:\\Users\\kodie\\OneDrive\\Bureaublad\\Artemis.jpg"; // Replace with the actual path to your image
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
        String hexString = bytesToHex(imageBytes);

        System.out.println(hexString);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hexStringBuilder.append(String.format("%02x", b));
        }
        return hexStringBuilder.toString();
    }
}

