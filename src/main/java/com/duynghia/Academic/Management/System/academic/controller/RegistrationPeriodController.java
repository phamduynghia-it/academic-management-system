package com.duynghia.Academic.Management.System.academic.controller;

import com.duynghia.Academic.Management.System.academic.dto.request.RegistrationPeriodCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.RegistrationPeriodResponse;
import com.duynghia.Academic.Management.System.academic.service.IRegistrationPeriodService;
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
@RequestMapping("/registration-period")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationPeriodController {
    IRegistrationPeriodService registrationPeriodService;

    @PostMapping
    public ApiResponse<RegistrationPeriodResponse> createRegistrationPeriod(@RequestBody @Valid RegistrationPeriodCreationRequest request) {
        return ApiResponse.<RegistrationPeriodResponse>builder()
                .result(registrationPeriodService.createRegistrationPeriod(request))
                .build();
    }
}
