package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCourseRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.ProgramUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramCourseResponse;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramResponse;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramWithCoursesResponse;
import com.duynghia.Academic.Management.System.academic.service.IProgramService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProgramController {

    IProgramService programService;

    @PostMapping
    public ApiResponse<ProgramResponse> createProgram(@RequestBody @Valid ProgramCreationRequest request) {
        return ApiResponse.<ProgramResponse>builder()
                .result(programService.createProgram(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProgramResponse>> getAllPrograms() {
        return ApiResponse.<List<ProgramResponse>>builder()
                .result(programService.getAllPrograms())
                .build();
    }

    @GetMapping("/{programId}")
    public ApiResponse<ProgramResponse> getProgramById(@PathVariable("programId") String programId) {
        return ApiResponse.<ProgramResponse>builder()
                .result(programService.getProgramById(programId))
                .build();
    }

    @PutMapping("/{programId}")
    public ApiResponse<ProgramResponse> updateProgram(
            @PathVariable("programId") String programId,
            @RequestBody @Valid ProgramUpdateRequest request) {
        return ApiResponse.<ProgramResponse>builder()
                .result(programService.updateProgram(programId, request))
                .build();
    }

    @DeleteMapping("/{programId}")
    public ApiResponse<String> deleteProgram(@PathVariable("programId") String programId) {
        programService.deleteProgram(programId);
        return ApiResponse.<String>builder()
                .result("Đã xóa chương trình đào tạo thành công")
                .build();
    }

    @PostMapping("/{programId}/courses")
    public ApiResponse<ProgramCourseResponse> addCourseToProgram(
            @PathVariable String programId,
            @RequestBody @Valid ProgramCourseRequest request) {
        return ApiResponse.<ProgramCourseResponse>builder()
                .result(programService.addCourseToProgram(programId, request))
                .build();
    }

    @GetMapping("/{programId}/courses")
    public ApiResponse<ProgramWithCoursesResponse> getProgramWithCourses(@PathVariable("programId") String programId) {
        return ApiResponse.<ProgramWithCoursesResponse>builder()
                .result(programService.getProgramDetails(programId))
                .build();
    }

    @DeleteMapping("/{programId}/courses/{courseId}")
    public ApiResponse<String> deleteCourseInProgram
            (@PathVariable("programId") String programId, @PathVariable("courseId") String courseId) {
        programService.deleteCourseInProgram(programId, courseId);
        return ApiResponse.<String>builder()
                .result("Đã xóa học phần ra khỏi chương trình đào tạo").build();
    }
}
