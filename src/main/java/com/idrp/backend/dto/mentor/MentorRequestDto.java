package com.idrp.backend.dto.mentor;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorRequestDto {

    @NotBlank(message = "Mentor name is required")
    @Size(max = 150, message = "Mentor name must not exceed 150 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @Size(max = 150, message = "Designation must not exceed 150 characters")
    private String designation;

    @Size(max = 150, message = "Organization must not exceed 150 characters")
    private String organization;

    private String expertise;

    private String bio;

    @Size(max = 255, message = "LinkedIn URL must not exceed 255 characters")
    private String linkedinUrl;

    private Boolean active;
}