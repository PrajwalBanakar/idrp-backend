package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.mentor.MentorRequestDto;
import com.idrp.backend.dto.mentor.MentorResponseDto;
import com.idrp.backend.service.MentorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MentorController {

    private final MentorService mentorService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<MentorResponseDto>> createMentor(
            @Valid @RequestBody MentorRequestDto requestDto
    ) {
        MentorResponseDto mentor = mentorService.createMentor(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<MentorResponseDto>builder()
                        .success(true)
                        .message("Mentor created successfully")
                        .data(mentor)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<MentorResponseDto>>> getAllMentors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<MentorResponseDto> mentors = mentorService.getAllMentors(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<MentorResponseDto>>builder()
                        .success(true)
                        .message("Mentors fetched successfully")
                        .data(mentors)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MentorResponseDto>> getMentorById(@PathVariable Long id) {
        MentorResponseDto mentor = mentorService.getMentorById(id);

        return ResponseEntity.ok(
                ApiResponse.<MentorResponseDto>builder()
                        .success(true)
                        .message("Mentor fetched successfully")
                        .data(mentor)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MentorResponseDto>> updateMentor(
            @PathVariable Long id,
            @Valid @RequestBody MentorRequestDto requestDto
    ) {
        MentorResponseDto mentor = mentorService.updateMentor(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<MentorResponseDto>builder()
                        .success(true)
                        .message("Mentor updated successfully")
                        .data(mentor)
                        .build()
        );
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentor(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Mentor deleted successfully")
                        .data(null)
                        .build()
        );
    }
}