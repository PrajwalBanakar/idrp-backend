package com.idrp.backend.service.impl;

import com.idrp.backend.dto.courseregistration.CourseRegistrationRequestDto;
import com.idrp.backend.dto.courseregistration.CourseRegistrationResponseDto;
import com.idrp.backend.entity.CourseRegistration;
import com.idrp.backend.entity.CourseRegistrationStatus;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.CourseRegistrationRepository;
import com.idrp.backend.service.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    private final CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public CourseRegistrationResponseDto createCourseRegistration(CourseRegistrationRequestDto requestDto) {

        if (courseRegistrationRepository.existsByEmailAndCourseType(
                requestDto.getEmail(),
                requestDto.getCourseType()
        )) {
            throw new DuplicateResourceException(
                    "Course registration already exists for email: " +
                            requestDto.getEmail() + " and course type: " + requestDto.getCourseType()
            );
        }

        CourseRegistration courseRegistration = mapToEntity(requestDto);
        CourseRegistration savedCourseRegistration = courseRegistrationRepository.save(courseRegistration);

        return mapToResponseDto(savedCourseRegistration);
    }

    @Override
    public Page<CourseRegistrationResponseDto> getAllCourseRegistrations(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.desc("submittedAt"))
        );

        return courseRegistrationRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public CourseRegistrationResponseDto getCourseRegistrationById(Long id) {
        CourseRegistration courseRegistration = courseRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course registration not found with id: " + id));

        return mapToResponseDto(courseRegistration);
    }

    @Override
    public CourseRegistrationResponseDto updateCourseRegistration(Long id, CourseRegistrationRequestDto requestDto) {

        CourseRegistration existingCourseRegistration = courseRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course registration not found with id: " + id));

        if (courseRegistrationRepository.existsByEmailAndCourseTypeAndIdNot(
                requestDto.getEmail(),
                requestDto.getCourseType(),
                id
        )) {
            throw new DuplicateResourceException(
                    "Another course registration already exists for email: " +
                            requestDto.getEmail() + " and course type: " + requestDto.getCourseType()
            );
        }

        existingCourseRegistration.setFullName(requestDto.getFullName());
        existingCourseRegistration.setEmail(requestDto.getEmail());
        existingCourseRegistration.setPhone(requestDto.getPhone());
        existingCourseRegistration.setOrganization(requestDto.getOrganization());
        existingCourseRegistration.setDesignation(requestDto.getDesignation());
        existingCourseRegistration.setCourseType(requestDto.getCourseType());
        existingCourseRegistration.setMessage(requestDto.getMessage());
        existingCourseRegistration.setStatus(
                requestDto.getStatus() != null ? requestDto.getStatus() : existingCourseRegistration.getStatus()
        );

        CourseRegistration updatedCourseRegistration = courseRegistrationRepository.save(existingCourseRegistration);

        return mapToResponseDto(updatedCourseRegistration);
    }

    @Override
    public void deleteCourseRegistration(Long id) {
        CourseRegistration courseRegistration = courseRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course registration not found with id: " + id));

        courseRegistrationRepository.delete(courseRegistration);
    }

    private CourseRegistration mapToEntity(CourseRegistrationRequestDto dto) {
        return CourseRegistration.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .organization(dto.getOrganization())
                .designation(dto.getDesignation())
                .courseType(dto.getCourseType())
                .message(dto.getMessage())
                .status(dto.getStatus() != null ? dto.getStatus() : CourseRegistrationStatus.PENDING)
                .build();
    }

    private CourseRegistrationResponseDto mapToResponseDto(CourseRegistration courseRegistration) {
        return CourseRegistrationResponseDto.builder()
                .id(courseRegistration.getId())
                .fullName(courseRegistration.getFullName())
                .email(courseRegistration.getEmail())
                .phone(courseRegistration.getPhone())
                .organization(courseRegistration.getOrganization())
                .designation(courseRegistration.getDesignation())
                .courseType(courseRegistration.getCourseType())
                .message(courseRegistration.getMessage())
                .status(courseRegistration.getStatus())
                .submittedAt(courseRegistration.getSubmittedAt())
                .updatedAt(courseRegistration.getUpdatedAt())
                .build();
    }
}