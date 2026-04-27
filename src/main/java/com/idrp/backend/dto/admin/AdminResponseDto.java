package com.idrp.backend.dto.admin;

import com.idrp.backend.entity.AdminRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponseDto {

    private Long id;
    private String name;
    private String email;
    private AdminRole role;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}