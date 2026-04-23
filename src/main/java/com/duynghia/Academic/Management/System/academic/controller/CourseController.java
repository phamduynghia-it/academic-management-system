package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseResponse;
import com.duynghia.Academic.Management.System.academic.service.ICourseService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {

    ICourseService courseService;

    @PostMapping
    public ApiResponse<CourseResponse> createCourse(@RequestBody @Valid CourseCreationRequest request) {
        return ApiResponse.<CourseResponse>builder()
                .result(courseService.createCourse(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CourseResponse>> getAllCourses() {
        return ApiResponse.<List<CourseResponse>>builder()
                .result(courseService.getAllCourses())
                .build();
    }

    @GetMapping("/{courseId}")
    public ApiResponse<CourseResponse> getCourseById(@PathVariable("courseId") String courseId) {
        return ApiResponse.<CourseResponse>builder()
                .result(courseService.getCourseById(courseId))
                .build();
    }

    @PutMapping("/{courseId}")
    public ApiResponse<CourseResponse> updateCourse(
            @PathVariable("courseId") String courseId,
            @RequestBody @Valid CourseUpdateRequest request) {
        return ApiResponse.<CourseResponse>builder()
                .result(courseService.updateCourse(courseId, request))
                .build();
    }

    @DeleteMapping("/{courseId}")
    public ApiResponse<String> deleteCourse(@PathVariable("courseId") String courseId) {
        courseService.deleteCourse(courseId);
        return ApiResponse.<String>builder()
                .result("Đã xóa học phần thành công khỏi hệ thống")
                .build();
    }
}