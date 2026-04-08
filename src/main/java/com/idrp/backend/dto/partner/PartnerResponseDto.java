package com.idrp.backend.dto.partner;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerResponseDto {

    private Long id;
    private String name;
    private String category;
    private String websiteUrl;
    private String logoUrl;
    private String description;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}