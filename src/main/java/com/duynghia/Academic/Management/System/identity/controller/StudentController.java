package com.duynghia.Academic.Management.System.identity.controller;

import com.duynghia.Academic.Management.System.academic.service.impl.StudentExcelService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import com.duynghia.Academic.Management.System.common.ImportResponse;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.dto.request.StudentCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.StudentResponse;
import com.duynghia.Academic.Management.System.identity.service.IStudentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    IStudentService studentService;
    StudentExcelService studentExcelService;

    @PostMapping
    public ApiResponse<StudentResponse> createStudent(@RequestBody @Valid StudentCreationRequest request) {
        return ApiResponse.<StudentResponse>builder()
                .result(studentService.createStudent(request))
                .build();
    }

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
        ImportResponse result = studentExcelService.importData(file);
        return ApiResponse.<ImportResponse>builder()
                .message("Xử lý file Excel hoàn tất")
                .result(result)
                .build();
    }
}
