package com.duynghia.Academic.Management.System.identity.controller;

import com.duynghia.Academic.Management.System.common.ApiResponse;
import com.duynghia.Academic.Management.System.identity.dto.request.StudentCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.StudentResponse;
import com.duynghia.Academic.Management.System.identity.service.IStudentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    IStudentService studentService;

    @PostMapping
    public ApiResponse<StudentResponse> createStudent(@RequestBody @Valid StudentCreationRequest request) {
        return ApiResponse.<StudentResponse>builder()
                .result(studentService.createStudent(request))
                .build();
    }
}
