package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseResponse;
import com.duynghia.Academic.Management.System.academic.service.ICourseService;
import com.duynghia.Academic.Management.System.academic.service.impl.CourseExcelService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import com.duynghia.Academic.Management.System.common.ImportResponse;
import com.duynghia.Academic.Management.System.common.PageResponse;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {

    ICourseService courseService;
    CourseExcelService courseExcelService;

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ImportResponse> importStudentsFromExcel(
            @RequestParam("file") MultipartFile file
    ) {
        if (file.isEmpty()) {
            throw new AppException(ErrorCode.FILE_IS_EMPTY);
        }
        String filename = file.getOriginalFilename();
        if (filename == null || (!filename.endsWith(".xlsx") && !filename.endsWith(".xls"))) {
            throw new AppException(ErrorCode.FILE_FORMAT_INVALID);
        }
        ImportResponse result = courseExcelService.importData(file);
        return ApiResponse.<ImportResponse>builder()
                .message("Xử lý file Excel hoàn tất")
                .result(result)
                .build();
    }

    @PostMapping
    public ApiResponse<CourseResponse> createCourse(@RequestBody @Valid CourseCreationRequest request) {
        return ApiResponse.<CourseResponse>builder()
                .result(courseService.createCourse(request))
                .build();
    }

    @GetMapping
    public ApiResponse<PageResponse<CourseResponse>> getAllCourses(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "departmentId", required = false) String departmentId) {
        return ApiResponse.<PageResponse<CourseResponse>>builder()
                .result(courseService.getAllCourses(page, size, keyword, departmentId))
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