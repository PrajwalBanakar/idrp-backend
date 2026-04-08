package com.idrp.backend.dto.mentor;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorResponseDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String designation;
    private String organization;
    private String expertise;
    private String bio;
    private String linkedinUrl;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}