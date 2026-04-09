package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCreationRequest;
import com.duynghia.Academic.Management.System.academic.entities.Program;
import com.duynghia.Academic.Management.System.academic.service.IProgramService;
import com.duynghia.Academic.Management.System.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProgramController {
    IProgramService programService;

    @PostMapping
    public ApiResponse<Program> createProgram(@RequestBody @Valid ProgramCreationRequest request) {
        return ApiResponse.<Program>builder()
                .result(programService.createProgram(request))
                .build();
    }
}
