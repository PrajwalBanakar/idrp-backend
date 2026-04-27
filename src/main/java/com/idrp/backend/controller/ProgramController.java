package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.program.ProgramRequestDto;
import com.idrp.backend.dto.program.ProgramResponseDto;
import com.idrp.backend.service.ProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProgramController {

    private final ProgramService programService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<ProgramResponseDto>> createProgram(
            @Valid @RequestBody ProgramRequestDto requestDto
    ) {
        ProgramResponseDto createdProgram = programService.createProgram(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<ProgramResponseDto>builder()
                        .success(true)
                        .message("Program created successfully")
                        .data(createdProgram)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProgramResponseDto>>> getAllPrograms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ProgramResponseDto> programs = programService.getAllPrograms(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<ProgramResponseDto>>builder()
                        .success(true)
                        .message("Programs fetched successfully")
                        .data(programs)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProgramResponseDto>> getProgramById(@PathVariable Long id) {
        ProgramResponseDto program = programService.getProgramById(id);

        return ResponseEntity.ok(
                ApiResponse.<ProgramResponseDto>builder()
                        .success(true)
                        .message("Program fetched successfully")
                        .data(program)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProgramResponseDto>> updateProgram(
            @PathVariable Long id,
            @Valid @RequestBody ProgramRequestDto requestDto
    ) {
        ProgramResponseDto updatedProgram = programService.updateProgram(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<ProgramResponseDto>builder()
                        .success(true)
                        .message("Program updated successfully")
                        .data(updatedProgram)
                        .build()
        );
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Program deleted successfully")
                        .data(null)
                        .build()
        );
    }
}