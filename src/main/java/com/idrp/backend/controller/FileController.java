package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.file.FileUploadResponseDto;
import com.idrp.backend.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FileController {

    private final FileStorageService fileStorageService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<FileUploadResponseDto>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "general") String folder
    ) {
        FileUploadResponseDto uploadedFile = fileStorageService.uploadFile(file, folder);

        return new ResponseEntity<>(
                ApiResponse.<FileUploadResponseDto>builder()
                        .success(true)
                        .message("File uploaded successfully")
                        .data(uploadedFile)
                        .build(),
                HttpStatus.CREATED
        );
    }
}