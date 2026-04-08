package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.startupapplication.StartupApplicationRequestDto;
import com.idrp.backend.dto.startupapplication.StartupApplicationResponseDto;
import com.idrp.backend.service.StartupApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/startup-applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StartupApplicationController {

    private final StartupApplicationService startupApplicationService;

    @PostMapping
    public ResponseEntity<ApiResponse<StartupApplicationResponseDto>> createStartupApplication(
            @Valid @RequestBody StartupApplicationRequestDto requestDto
    ) {
        StartupApplicationResponseDto startupApplication =
                startupApplicationService.createStartupApplication(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<StartupApplicationResponseDto>builder()
                        .success(true)
                        .message("Startup application submitted successfully")
                        .data(startupApplication)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<StartupApplicationResponseDto>>> getAllStartupApplications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<StartupApplicationResponseDto> startupApplications =
                startupApplicationService.getAllStartupApplications(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<StartupApplicationResponseDto>>builder()
                        .success(true)
                        .message("Startup applications fetched successfully")
                        .data(startupApplications)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StartupApplicationResponseDto>> getStartupApplicationById(
            @PathVariable Long id
    ) {
        StartupApplicationResponseDto startupApplication =
                startupApplicationService.getStartupApplicationById(id);

        return ResponseEntity.ok(
                ApiResponse.<StartupApplicationResponseDto>builder()
                        .success(true)
                        .message("Startup application fetched successfully")
                        .data(startupApplication)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StartupApplicationResponseDto>> updateStartupApplication(
            @PathVariable Long id,
            @Valid @RequestBody StartupApplicationRequestDto requestDto
    ) {
        StartupApplicationResponseDto startupApplication =
                startupApplicationService.updateStartupApplication(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<StartupApplicationResponseDto>builder()
                        .success(true)
                        .message("Startup application updated successfully")
                        .data(startupApplication)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStartupApplication(@PathVariable Long id) {
        startupApplicationService.deleteStartupApplication(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Startup application deleted successfully")
                        .data(null)
                        .build()
        );
    }
}