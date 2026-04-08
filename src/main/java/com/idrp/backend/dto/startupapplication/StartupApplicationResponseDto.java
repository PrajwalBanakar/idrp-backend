package com.idrp.backend.dto.startupapplication;

import com.idrp.backend.entity.StartupApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartupApplicationResponseDto {

    private Long id;
    private String startupName;
    private String founderName;
    private String email;
    private String phone;
    private String sector;
    private String stage;
    private String problemStatement;
    private String solution;
    private String websiteUrl;
    private String pitchDeckUrl;
    private StartupApplicationStatus status;
    private LocalDateTime submittedAt;
    private LocalDateTime updatedAt;
}