package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.repository.EnrollmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentCourseCacheService {
    EnrollmentRepository enrollmentRepository;

    @Cacheable(value = "passed_or_registed_courses", key = "#studentId")
    public List<String> getPassedOrRegisteredCourseIds(String studentId) {
        return enrollmentRepository.findPassedOrRegisteredCourseIds(studentId);
    }

    @Cacheable(value = "passed_courses", key = "#studentId")
    public List<String> getPassedCourseIds(String studentId) {
        return enrollmentRepository.findPassedCourseIds(studentId);
    }

    @CacheEvict(value = {"passed_or_registed_courses", "passed_courses"}, key = "#studentId")
    public void evictStudentCourseCache(String studentId) {
    }
}