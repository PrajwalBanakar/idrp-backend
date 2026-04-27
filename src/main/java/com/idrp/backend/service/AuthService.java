package com.idrp.backend.service;

import com.idrp.backend.dto.auth.AdminLoginRequestDto;
import com.idrp.backend.dto.auth.AuthResponseDto;
import com.idrp.backend.dto.auth.CreateAdminRequestDto;

public interface AuthService {

    AuthResponseDto createAdmin(CreateAdminRequestDto requestDto);

    AuthResponseDto login(AdminLoginRequestDto requestDto);

    AuthResponseDto refreshAccessToken(String refreshToken);

    void logout(String refreshToken);
}