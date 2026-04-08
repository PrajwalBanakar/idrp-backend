package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.startup.StartupRequestDto;
import com.idrp.backend.dto.startup.StartupResponseDto;
import com.idrp.backend.service.StartupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/startups")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StartupController {

    private final StartupService startupService;

    @PostMapping
    public ResponseEntity<ApiResponse<StartupResponseDto>> createStartup(
            @Valid @RequestBody StartupRequestDto requestDto
    ) {
        StartupResponseDto createdStartup = startupService.createStartup(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<StartupResponseDto>builder()
                        .success(true)
                        .message("Startup created successfully")
                        .data(createdStartup)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<StartupResponseDto>>> getAllStartups(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<StartupResponseDto> startups = startupService.getAllStartups(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<StartupResponseDto>>builder()
                        .success(true)
                        .message("Startups fetched successfully")
                        .data(startups)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StartupResponseDto>> getStartupById(@PathVariable Long id) {
        StartupResponseDto startup = startupService.getStartupById(id);

        return ResponseEntity.ok(
                ApiResponse.<StartupResponseDto>builder()
                        .success(true)
                        .message("Startup fetched successfully")
                        .data(startup)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StartupResponseDto>> updateStartup(
            @PathVariable Long id,
            @Valid @RequestBody StartupRequestDto requestDto
    ) {
        StartupResponseDto updatedStartup = startupService.updateStartup(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<StartupResponseDto>builder()
                        .success(true)
                        .message("Startup updated successfully")
                        .data(updatedStartup)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStartup(@PathVariable Long id) {
        startupService.deleteStartup(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Startup deleted successfully")
                        .data(null)
                        .build()
        );
    }
}