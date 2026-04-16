package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.dto.request.DepartmentCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.DepartmentUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.DepartmentResponse;
import com.duynghia.Academic.Management.System.academic.service.IDepartmentService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {
    IDepartmentService departmentService;

    @PostMapping
    ApiResponse<DepartmentResponse> createDepartment(@RequestBody @Valid DepartmentCreationRequest request) {
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.createDepartment(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<DepartmentResponse>> getAllDepartments() {
        return ApiResponse.<List<DepartmentResponse>>builder()
                .result(departmentService.getAllDepartments())
                .build();
    }

    @GetMapping("/{departmentId}")
    public ApiResponse<DepartmentResponse> getDepartmentById(@PathVariable("departmentId") String departmentId) {
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.getDepartmentById(departmentId))
                .build();
    }

    @PutMapping("/{departmentId}")
    public ApiResponse<DepartmentResponse> updateDepartment(
            @PathVariable("departmentId") String departmentId,
            @RequestBody @Valid DepartmentUpdateRequest request) {
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.updateDepartment(departmentId, request))
                .build();
    }

    @DeleteMapping("/{departmentId}")
    public ApiResponse<String> deleteDepartment(@PathVariable("departmentId") String departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ApiResponse.<String>builder()
                .result("Đã xóa thành công bộ môn/phòng ban khỏi hệ thống")
                .build();
    }
}
