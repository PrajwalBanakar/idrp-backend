package com.idrp.backend.dto.file;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileUploadResponseDto {

    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long fileSize;
}