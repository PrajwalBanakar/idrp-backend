package com.idrp.backend.dto.teammember;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberResponseDto {

    private Long id;
    private String name;
    private String designation;
    private String department;
    private String bio;
    private String email;
    private String phone;
    private String profileImageUrl;
    private String linkedinUrl;
    private Integer displayOrder;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}