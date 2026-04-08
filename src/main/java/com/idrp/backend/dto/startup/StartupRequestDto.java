package com.idrp.backend.dto.startup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartupRequestDto {

    @NotBlank(message = "Startup name is required")
    @Size(max = 150, message = "Startup name must not exceed 150 characters")
    private String startupName;

    @NotBlank(message = "Founder name is required")
    @Size(max = 120, message = "Founder name must not exceed 120 characters")
    private String founderName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @Size(max = 100, message = "Sector must not exceed 100 characters")
    private String sector;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
}