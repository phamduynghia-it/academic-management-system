package com.duynghia.Academic.Management.System.academic.service.impl;


import com.duynghia.Academic.Management.System.academic.dto.request.CourseCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseResponse;
import com.duynghia.Academic.Management.System.academic.entities.Course;
import com.duynghia.Academic.Management.System.academic.entities.Department;
import com.duynghia.Academic.Management.System.academic.mapper.CourseMapper;
import com.duynghia.Academic.Management.System.academic.repository.CourseRepository;
import com.duynghia.Academic.Management.System.academic.repository.DepartmentRepository;
import com.duynghia.Academic.Management.System.academic.service.ICourseService;
import com.duynghia.Academic.Management.System.common.PageResponse;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService implements ICourseService {

    CourseRepository courseRepository;
    DepartmentRepository departmentRepository;
    CourseMapper courseMapper;

    @Override
    public CourseResponse createCourse(CourseCreationRequest request) {
        if (courseRepository.existsById(request.getCourseId())) {
            throw new AppException(ErrorCode.COURSE_EXISTED);
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

        Course course = courseMapper.toCourse(request);
        course.setDepartment(department);

        return courseMapper.toCourseResponse(courseRepository.save(course));
    }

    @Override
    public PageResponse<CourseResponse> getAllCourses(int page, int size, String keyword, String departmentId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "courseName");

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Course> coursePage = courseRepository.seacrhAndFilterCourse(keyword, departmentId, pageable);

        List<CourseResponse> courseResponseList = coursePage.getContent().stream()
                .map(courseMapper::toCourseResponse).toList();
        return PageResponse.<CourseResponse>builder()
                .currentPage(page)
                .pageSize(coursePage.getSize())
                .data(courseResponseList)
                .totalPages(coursePage.getTotalPages())
                .totalElements(coursePage.getTotalElements())
                .build();
    }

    @Override
    public CourseResponse getCourseById(String id) {
        return courseRepository.findById(id)
                .map(courseMapper::toCourseResponse)
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
    }

    @Override
    public CourseResponse updateCourse(String id, CourseUpdateRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        courseMapper.updateCourseFromRequest(course, request);

        return courseMapper.toCourseResponse(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(String id) {
        if (!courseRepository.existsById(id)) {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        courseRepository.deleteById(id);
    }
}
