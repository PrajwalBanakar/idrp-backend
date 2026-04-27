package com.idrp.backend.service;

import com.idrp.backend.dto.file.FileUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    FileUploadResponseDto uploadFile(MultipartFile file, String folder);
}