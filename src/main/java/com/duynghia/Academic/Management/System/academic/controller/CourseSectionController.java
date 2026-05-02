package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseSectionResponse;
import com.duynghia.Academic.Management.System.academic.enums.CourseSectionStatus;
import com.duynghia.Academic.Management.System.academic.service.ICourseSectionService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import com.duynghia.Academic.Management.System.common.PageResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-sections")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseSectionController {
    ICourseSectionService courseSectionService;

    @PostMapping
    public ApiResponse<CourseSectionResponse> createCourseSection(@RequestBody @Valid CourseSectionCreationRequest request) {
        return ApiResponse.<CourseSectionResponse>builder()
                .result(courseSectionService.createCourseSection(request))
                .build();
    }

    @PutMapping("/{courseSectionId}")
    public ApiResponse<CourseSectionResponse>
    updateCourseSection(@PathVariable("courseSectionId") String courseSectionId,
                        @RequestBody @Valid CourseSectionUpdateRequest request) {
        return ApiResponse.<CourseSectionResponse>builder()
                .result(courseSectionService.updateCourseSection(courseSectionId, request))
                .build();
    }

    @GetMapping
    public ApiResponse<PageResponse<CourseSectionResponse>> getAllSections(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "status", required = false) CourseSectionStatus status,
            @RequestParam(value = "academicYear", required = false) String academicYear,
            @RequestParam(value = "semester", required = false) Integer semester,
            @RequestParam(value = "phase", required = false) Integer phase) {

        return ApiResponse.<PageResponse<CourseSectionResponse>>builder()
                .result(courseSectionService.getAllSections(page, size, keyword, academicYear, semester, phase, status))
                .build();
    }

    @GetMapping("/available")
    public ApiResponse<PageResponse<CourseSectionResponse>> getAvailableSections(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ApiResponse.<PageResponse<CourseSectionResponse>>builder()
                .result(courseSectionService.getAvailableSections(page, size))
                .build();
    }
}
