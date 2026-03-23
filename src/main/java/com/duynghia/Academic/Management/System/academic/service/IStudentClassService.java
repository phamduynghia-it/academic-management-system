package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.StudentClassCreationRequest;
import com.duynghia.Academic.Management.System.academic.entities.StudentClass;

public interface IStudentClassService {
    public StudentClass createClass(StudentClassCreationRequest request);
}
