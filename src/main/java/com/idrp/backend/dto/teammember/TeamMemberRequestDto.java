package com.idrp.backend.dto.teammember;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberRequestDto {

    @NotBlank(message = "Team member name is required")
    @Size(max = 150, message = "Name must not exceed 150 characters")
    private String name;

    @Size(max = 150, message = "Designation must not exceed 150 characters")
    private String designation;

    @Size(max = 100, message = "Department must not exceed 100 characters")
    private String department;

    private String bio;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @Size(max = 255, message = "Profile image URL must not exceed 255 characters")
    private String profileImageUrl;

    @Size(max = 255, message = "LinkedIn URL must not exceed 255 characters")
    private String linkedinUrl;

    private Integer displayOrder;

    private Boolean active;
}