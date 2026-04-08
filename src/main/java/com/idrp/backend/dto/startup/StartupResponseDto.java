package com.idrp.backend.dto.startup;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartupResponseDto {

    private Long id;
    private String startupName;
    private String founderName;
    private String email;
    private String phone;
    private String sector;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}