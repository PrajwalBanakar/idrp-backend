package com.idrp.backend.dto.programapplication;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgramApplicationResponseDto {

    private Long id;
    private String applicantName;
    private String email;
    private String phone;
    private String organization;
    private String ideaTitle;
    private String stage;
    private String message;
    private Long programId;
    private String programName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}