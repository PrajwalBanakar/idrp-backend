package com.idrp.backend.dto.resource;

import com.idrp.backend.entity.ResourceType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceResponseDto {

    private Long id;
    private String title;
    private String slug;
    private ResourceType type;
    private String summary;
    private String content;
    private String fileUrl;
    private String coverImageUrl;
    private LocalDate publishDate;
    private String author;
    private Integer displayOrder;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}