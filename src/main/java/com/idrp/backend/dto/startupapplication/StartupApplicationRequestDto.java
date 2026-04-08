package com.idrp.backend.dto.startupapplication;

import com.idrp.backend.entity.StartupApplicationStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartupApplicationRequestDto {

    @NotBlank(message = "Startup name is required")
    @Size(max = 150)
    private String startupName;

    @NotBlank(message = "Founder name is required")
    @Size(max = 120)
    private String founderName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    @Size(max = 150)
    private String email;

    @Size(max = 20)
    private String phone;

    @Size(max = 100)
    private String sector;

    @Size(max = 100)
    private String stage;

    private String problemStatement;

    private String solution;

    @Size(max = 255)
    private String websiteUrl;

    @Size(max = 255)
    private String pitchDeckUrl;

    private StartupApplicationStatus status;
}