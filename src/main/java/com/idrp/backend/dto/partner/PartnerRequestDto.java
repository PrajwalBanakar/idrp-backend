package com.idrp.backend.dto.partner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerRequestDto {

    @NotBlank(message = "Partner name is required")
    @Size(max = 150, message = "Partner name must not exceed 150 characters")
    private String name;

    @Size(max = 100, message = "Category must not exceed 100 characters")
    private String category;

    @Size(max = 255, message = "Website URL must not exceed 255 characters")
    private String websiteUrl;

    @Size(max = 255, message = "Logo URL must not exceed 255 characters")
    private String logoUrl;

    private String description;

    @Size(max = 120, message = "Contact person must not exceed 120 characters")
    private String contactPerson;

    @Email(message = "Enter a valid contact email address")
    @Size(max = 150, message = "Contact email must not exceed 150 characters")
    private String contactEmail;

    @Size(max = 20, message = "Contact phone must not exceed 20 characters")
    private String contactPhone;

    private Boolean active;
}