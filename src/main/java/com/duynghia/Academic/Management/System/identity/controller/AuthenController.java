package com.duynghia.Academic.Management.System.identity.controller;

import com.duynghia.Academic.Management.System.common.ApiResponse;
import com.duynghia.Academic.Management.System.identity.dto.request.AuthenticationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.AuthenticationResponse;
import com.duynghia.Academic.Management.System.identity.service.IAuthenService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenController {
    IAuthenService authenService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenService.authenticate(request))
                .build();
    }
}
