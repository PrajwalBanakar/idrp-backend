package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.courseregistration.CourseRegistrationRequestDto;
import com.idrp.backend.dto.courseregistration.CourseRegistrationResponseDto;
import com.idrp.backend.service.CourseRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-registrations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CourseRegistrationController {

    private final CourseRegistrationService courseRegistrationService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseRegistrationResponseDto>> createCourseRegistration(
            @Valid @RequestBody CourseRegistrationRequestDto requestDto
    ) {
        CourseRegistrationResponseDto courseRegistration =
                courseRegistrationService.createCourseRegistration(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<CourseRegistrationResponseDto>builder()
                        .success(true)
                        .message("Course registration submitted successfully")
                        .data(courseRegistration)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CourseRegistrationResponseDto>>> getAllCourseRegistrations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CourseRegistrationResponseDto> courseRegistrations =
                courseRegistrationService.getAllCourseRegistrations(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<CourseRegistrationResponseDto>>builder()
                        .success(true)
                        .message("Course registrations fetched successfully")
                        .data(courseRegistrations)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseRegistrationResponseDto>> getCourseRegistrationById(
            @PathVariable Long id
    ) {
        CourseRegistrationResponseDto courseRegistration =
                courseRegistrationService.getCourseRegistrationById(id);

        return ResponseEntity.ok(
                ApiResponse.<CourseRegistrationResponseDto>builder()
                        .success(true)
                        .message("Course registration fetched successfully")
                        .data(courseRegistration)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseRegistrationResponseDto>> updateCourseRegistration(
            @PathVariable Long id,
            @Valid @RequestBody CourseRegistrationRequestDto requestDto
    ) {
        CourseRegistrationResponseDto courseRegistration =
                courseRegistrationService.updateCourseRegistration(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<CourseRegistrationResponseDto>builder()
                        .success(true)
                        .message("Course registration updated successfully")
                        .data(courseRegistration)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourseRegistration(@PathVariable Long id) {
        courseRegistrationService.deleteCourseRegistration(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Course registration deleted successfully")
                        .data(null)
                        .build()
        );
    }
}