package com.duynghia.Academic.Management.System.identity.service;

import com.duynghia.Academic.Management.System.identity.dto.request.StudentCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.StudentResponse;

public interface IStudentService {
    StudentResponse createStudent(StudentCreationRequest request);
}
