package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.programapplication.ProgramApplicationRequestDto;
import com.idrp.backend.dto.programapplication.ProgramApplicationResponseDto;
import com.idrp.backend.service.ProgramApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/program-applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProgramApplicationController {

    private final ProgramApplicationService programApplicationService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProgramApplicationResponseDto>> createProgramApplication(
            @Valid @RequestBody ProgramApplicationRequestDto requestDto
    ) {
        ProgramApplicationResponseDto createdProgramApplication =
                programApplicationService.createProgramApplication(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<ProgramApplicationResponseDto>builder()
                        .success(true)
                        .message("Program application created successfully")
                        .data(createdProgramApplication)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProgramApplicationResponseDto>>> getAllProgramApplications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ProgramApplicationResponseDto> programApplications =
                programApplicationService.getAllProgramApplications(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<ProgramApplicationResponseDto>>builder()
                        .success(true)
                        .message("Program applications fetched successfully")
                        .data(programApplications)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProgramApplicationResponseDto>> getProgramApplicationById(
            @PathVariable Long id
    ) {
        ProgramApplicationResponseDto programApplication =
                programApplicationService.getProgramApplicationById(id);

        return ResponseEntity.ok(
                ApiResponse.<ProgramApplicationResponseDto>builder()
                        .success(true)
                        .message("Program application fetched successfully")
                        .data(programApplication)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProgramApplicationResponseDto>> updateProgramApplication(
            @PathVariable Long id,
            @Valid @RequestBody ProgramApplicationRequestDto requestDto
    ) {
        ProgramApplicationResponseDto updatedProgramApplication =
                programApplicationService.updateProgramApplication(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<ProgramApplicationResponseDto>builder()
                        .success(true)
                        .message("Program application updated successfully")
                        .data(updatedProgramApplication)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProgramApplication(@PathVariable Long id) {
        programApplicationService.deleteProgramApplication(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Program application deleted successfully")
                        .data(null)
                        .build()
        );
    }
}