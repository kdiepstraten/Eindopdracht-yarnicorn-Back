package com.example.eindopdrachtyarnicornback.Repository;

import com.example.eindopdrachtyarnicornback.Models.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface DocFileRepository extends JpaRepository<FileDocument, Long> {
    FileDocument findByFileName(String fileName);
}
