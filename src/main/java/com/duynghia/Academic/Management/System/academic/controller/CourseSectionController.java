package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseSectionResponse;
import com.duynghia.Academic.Management.System.academic.service.ICourseSectionService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
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
}
