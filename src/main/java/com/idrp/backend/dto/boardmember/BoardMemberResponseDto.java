package com.idrp.backend.dto.boardmember;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardMemberResponseDto {

    private Long id;
    private String name;
    private String designation;
    private String boardType;
    private String organization;
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