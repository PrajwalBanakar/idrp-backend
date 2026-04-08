package com.idrp.backend.controller;

import com.idrp.backend.dto.auth.AdminLoginRequestDto;
import com.idrp.backend.dto.auth.AuthResponseDto;
import com.idrp.backend.dto.auth.CreateAdminRequestDto;
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
}