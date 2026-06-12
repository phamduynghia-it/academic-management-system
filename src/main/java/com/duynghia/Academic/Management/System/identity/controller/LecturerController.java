package com.duynghia.Academic.Management.System.identity.controller;

import com.duynghia.Academic.Management.System.academic.service.impl.LecturerExcelService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import com.duynghia.Academic.Management.System.common.ImportResponse;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.dto.request.LectureCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.LectureResponse;
import com.duynghia.Academic.Management.System.identity.service.ILectureService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LecturerController {
    ILectureService lectureService;
    LecturerExcelService lecturerExcelService;

    @PostMapping
    public ApiResponse<LectureResponse> createLecture(@RequestBody @Valid LectureCreationRequest request) {
        return ApiResponse.<LectureResponse>builder()
                .result(lectureService.createLecture(request))
                .build();
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ImportResponse> importLecturersFromExcel(
            @RequestParam("file") MultipartFile file
    ) {
        if (file.isEmpty()) {
            throw new AppException(ErrorCode.FILE_IS_EMPTY);
        }
        if (file.getOriginalFilename() == null || !file.getOriginalFilename().endsWith(".xlsx")) {
            throw new AppException(ErrorCode.FILE_FORMAT_INVALID);
        }
        ImportResponse result = lecturerExcelService.importData(file);
        return ApiResponse.<ImportResponse>builder()
                .message("Xử lý file Excel hoàn tất")
                .result(result)
                .build();
    }
}
