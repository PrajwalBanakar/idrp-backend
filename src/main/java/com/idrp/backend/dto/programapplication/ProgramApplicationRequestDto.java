package com.idrp.backend.dto.programapplication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgramApplicationRequestDto {

    @NotBlank(message = "Applicant name is required")
    @Size(max = 120, message = "Applicant name must not exceed 120 characters")
    private String applicantName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @Size(max = 150, message = "Organization must not exceed 150 characters")
    private String organization;

    @Size(max = 150, message = "Idea title must not exceed 150 characters")
    private String ideaTitle;

    @Size(max = 100, message = "Stage must not exceed 100 characters")
    private String stage;

    @Size(max = 1000, message = "Message must not exceed 1000 characters")
    private String message;

    @NotNull(message = "Program id is required")
    private Long programId;
}