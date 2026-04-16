package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.dto.request.FacultyCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.FacultyUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.FacultyResponse;
import com.duynghia.Academic.Management.System.academic.service.IFacultyService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacultyController {
    IFacultyService facultyService;

    @PostMapping
    public ApiResponse<FacultyResponse> createFaculty(@RequestBody @Valid FacultyCreationRequest request) {
        return ApiResponse.<FacultyResponse>builder()
                .result(facultyService.createFaculty(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<FacultyResponse>> getAllFaculty() {
        return ApiResponse.<List<FacultyResponse>>builder()
                .result(facultyService.getAllFaculties())
                .build();
    }

    @GetMapping("/{facultyId}")
    public ApiResponse<FacultyResponse> getFacultyById(@PathVariable("facultyId") String facultyId) {
        return ApiResponse.<FacultyResponse>builder()
                .result(facultyService.getFacultyById(facultyId))
                .build();
    }

    @PutMapping("/{facultyId}")
    public ApiResponse<FacultyResponse>
    updateFaculty(@PathVariable("facultyId") String facultyId, @RequestBody @Valid FacultyUpdateRequest request) {
        return ApiResponse.<FacultyResponse>builder()
                .result(facultyService.updateFaculty(facultyId, request))
                .build();
    }

    @DeleteMapping("/{facultyId}")
    public ApiResponse<String> deleteFaculty(@PathVariable("facultyId") String facultyId) {
        facultyService.deleteFaculty(facultyId);
        return ApiResponse.<String>builder()
                .result("Đã xóa thành công khoa khỏi hệ thống")
                .build();
    }

}
