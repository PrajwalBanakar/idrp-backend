package com.idrp.backend.dto.auth;

import com.idrp.backend.entity.AdminRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDto {

    private Long adminId;
    private String name;
    private String email;
    private AdminRole role;

    private String accessToken;
    private String refreshToken;
    private String tokenType;
}