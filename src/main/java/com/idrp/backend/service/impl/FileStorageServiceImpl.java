package com.idrp.backend.service.impl;

import com.idrp.backend.dto.file.FileUploadResponseDto;
import com.idrp.backend.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.upload.base-url}")
    private String uploadBaseUrl;

    @Override
    public FileUploadResponseDto uploadFile(MultipartFile file, String folder) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            String originalFileName = file.getOriginalFilename();
            String extension = "";

            if (originalFileName != null && originalFileName.contains(".")) {
                extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }

            String storedFileName = UUID.randomUUID() + extension;

            Path folderPath = Paths.get(uploadDir, folder);
            Files.createDirectories(folderPath);

            Path filePath = folderPath.resolve(storedFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = uploadBaseUrl + "/" + folder + "/" + storedFileName;

            return FileUploadResponseDto.builder()
                    .fileName(storedFileName)
                    .fileUrl(fileUrl)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage());
        }
    }
}