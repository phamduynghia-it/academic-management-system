package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.dto.request.StudentClassCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.StudentClassUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.StudentClassResponse;
import com.duynghia.Academic.Management.System.academic.service.IStudentClassService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentClassController {

    IStudentClassService studentClassService;

    @PostMapping
    public ApiResponse<StudentClassResponse> createClass(@RequestBody @Valid StudentClassCreationRequest request) {
        return ApiResponse.<StudentClassResponse>builder()
                .result(studentClassService.createClass(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<StudentClassResponse>> getAllClasses() {
        return ApiResponse.<List<StudentClassResponse>>builder()
                .result(studentClassService.getAllClasses())
                .build();
    }

    @GetMapping("/{classId}")
    public ApiResponse<StudentClassResponse> getClassById(@PathVariable("classId") String classId) {
        return ApiResponse.<StudentClassResponse>builder()
                .result(studentClassService.getClassById(classId))
                .build();
    }

    @PutMapping("/{classId}")
    public ApiResponse<StudentClassResponse> updateClass(
            @PathVariable("classId") String classId,
            @RequestBody @Valid StudentClassUpdateRequest request) {
        return ApiResponse.<StudentClassResponse>builder()
                .result(studentClassService.updateClass(classId, request))
                .build();
    }

    @DeleteMapping("/{classId}")
    public ApiResponse<String> deleteClass(@PathVariable("classId") String classId) {
        studentClassService.deleteClass(classId);
        return ApiResponse.<String>builder()
                .result("Đã xóa lớp thành công khỏi hệ thống")
                .build();
    }
}
