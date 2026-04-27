package com.idrp.backend.controller;

import com.idrp.backend.dto.admin.AdminRequestDto;
import com.idrp.backend.dto.admin.AdminResponseDto;
import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<AdminResponseDto>> createAdmin(
            @Valid @RequestBody AdminRequestDto requestDto
    ) {
        AdminResponseDto admin = adminService.createAdmin(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<AdminResponseDto>builder()
                        .success(true)
                        .message("Admin created successfully")
                        .data(admin)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<AdminResponseDto>>> getAllAdmins(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<AdminResponseDto> admins = adminService.getAllAdmins(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<AdminResponseDto>>builder()
                        .success(true)
                        .message("Admins fetched successfully")
                        .data(admins)
                        .build()
        );
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminResponseDto>> getAdminById(@PathVariable Long id) {
        AdminResponseDto admin = adminService.getAdminById(id);

        return ResponseEntity.ok(
                ApiResponse.<AdminResponseDto>builder()
                        .success(true)
                        .message("Admin fetched successfully")
                        .data(admin)
                        .build()
        );
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminResponseDto>> updateAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AdminRequestDto requestDto
    ) {
        AdminResponseDto admin = adminService.updateAdmin(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<AdminResponseDto>builder()
                        .success(true)
                        .message("Admin updated successfully")
                        .data(admin)
                        .build()
        );
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Admin deleted successfully")
                        .data(null)
                        .build()
        );
    }
}