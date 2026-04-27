package com.idrp.backend.controller;

import com.idrp.backend.dto.auth.AdminLoginRequestDto;
import com.idrp.backend.dto.auth.AuthResponseDto;
import com.idrp.backend.dto.auth.CreateAdminRequestDto;
import com.idrp.backend.dto.auth.RefreshTokenRequestDto;
import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponseDto>> createAdmin(
            @Valid @RequestBody CreateAdminRequestDto requestDto
    ) {
        AuthResponseDto response = authService.createAdmin(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<AuthResponseDto>builder()
                        .success(true)
                        .message("Admin created successfully")
                        .data(response)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(
            @Valid @RequestBody AdminLoginRequestDto requestDto
    ) {
        AuthResponseDto response = authService.login(requestDto);

        return ResponseEntity.ok(
                ApiResponse.<AuthResponseDto>builder()
                        .success(true)
                        .message("Login successful")
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthResponseDto>> refreshAccessToken(
            @Valid @RequestBody RefreshTokenRequestDto requestDto
    ) {
        AuthResponseDto response = authService.refreshAccessToken(requestDto.getRefreshToken());

        return ResponseEntity.ok(
                ApiResponse.<AuthResponseDto>builder()
                        .success(true)
                        .message("Access token refreshed successfully")
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(
            @Valid @RequestBody RefreshTokenRequestDto requestDto
    ) {
        authService.logout(requestDto.getRefreshToken());

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Logout successful")
                        .data(null)
                        .build()
        );
    }
}