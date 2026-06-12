package com.duynghia.Academic.Management.System.identity.service;

import com.duynghia.Academic.Management.System.identity.dto.request.StudentCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.StudentResponse;
import jakarta.validation.Valid;

public interface IStudentService {
    StudentResponse createStudent(@Valid StudentCreationRequest request);
}
