package com.idrp.backend.repository;

import com.idrp.backend.entity.CourseRegistration;
import com.idrp.backend.entity.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

    boolean existsByEmailAndCourseType(String email, CourseType courseType);

    boolean existsByEmailAndCourseTypeAndIdNot(String email, CourseType courseType, Long id);
}