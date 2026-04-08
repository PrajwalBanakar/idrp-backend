package com.idrp.backend.dto.program;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgramRequestDto {

    @NotBlank(message = "Program name is required")
    @Size(max = 150, message = "Program name must not exceed 150 characters")
    private String programName;

    @Size(max = 100, message = "Category must not exceed 100 characters")
    private String category;

    @Size(max = 100, message = "Duration must not exceed 100 characters")
    private String duration;

    @Size(max = 50, message = "Mode must not exceed 50 characters")
    private String mode;

    @Size(max = 255, message = "Eligibility must not exceed 255 characters")
    private String eligibility;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
}