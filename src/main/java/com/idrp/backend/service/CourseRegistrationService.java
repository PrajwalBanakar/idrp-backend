package com.idrp.backend.service;

import com.idrp.backend.dto.courseregistration.CourseRegistrationRequestDto;
import com.idrp.backend.dto.courseregistration.CourseRegistrationResponseDto;
import org.springframework.data.domain.Page;

public interface CourseRegistrationService {

    CourseRegistrationResponseDto createCourseRegistration(CourseRegistrationRequestDto requestDto);

    Page<CourseRegistrationResponseDto> getAllCourseRegistrations(int page, int size);

    CourseRegistrationResponseDto getCourseRegistrationById(Long id);

    CourseRegistrationResponseDto updateCourseRegistration(Long id, CourseRegistrationRequestDto requestDto);

    void deleteCourseRegistration(Long id);
}