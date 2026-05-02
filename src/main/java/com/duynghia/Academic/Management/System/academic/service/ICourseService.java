package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseResponse;
import com.duynghia.Academic.Management.System.common.PageResponse;

public interface ICourseService {
    public CourseResponse createCourse(CourseCreationRequest request);

    public PageResponse<CourseResponse> getAllCourses(int page, int size, String keyword, String departmentId);

    public CourseResponse getCourseById(String id);

    public CourseResponse updateCourse(String id, CourseUpdateRequest request);

    public void deleteCourse(String id);
}
