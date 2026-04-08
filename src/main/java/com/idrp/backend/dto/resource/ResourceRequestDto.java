package com.idrp.backend.dto.resource;

import com.idrp.backend.entity.ResourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceRequestDto {

    @NotBlank(message = "Title is required")
    @Size(max = 200)
    private String title;

    @NotBlank(message = "Slug is required")
    @Size(max = 220)
    private String slug;

    @NotNull(message = "Resource type is required")
    private ResourceType type;

    private String summary;

    private String content;

    @Size(max = 255)
    private String fileUrl;

    @Size(max = 255)
    private String coverImageUrl;

    private LocalDate publishDate;

    @Size(max = 150)
    private String author;

    private Boolean active;

    private Integer displayOrder;
}