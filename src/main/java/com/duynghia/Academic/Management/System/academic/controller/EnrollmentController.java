package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.service.IEnrollmentService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EnrollmentController {

    IEnrollmentService enrollmentService;

    @PostMapping("/{sectionId}")
    public ApiResponse<String> registerCourseSection(@PathVariable("sectionId") String sectionId) {
        enrollmentService.registerCourseSection(sectionId);
        return ApiResponse.<String>builder()
                .result("Course section registered successfully")
                .build();
    }

    @DeleteMapping("/{sectionId}")
    public ApiResponse<String> cancelCourseSection(@PathVariable("sectionId") String sectionId) {
        enrollmentService.cancelCourseSection(sectionId);
        return ApiResponse.<String>builder()
                .result("Course section registration cancelled successfully")
                .build();
    }
}
