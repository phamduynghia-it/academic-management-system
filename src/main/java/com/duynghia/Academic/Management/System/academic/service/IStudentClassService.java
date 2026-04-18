package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.StudentClassCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.StudentClassUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.StudentClassResponse;

import java.util.List;

public interface IStudentClassService {
    public StudentClassResponse createClass(StudentClassCreationRequest request);

    public List<StudentClassResponse> getAllClasses();

    public StudentClassResponse getClassById(String id);

    public StudentClassResponse updateClass(String id, StudentClassUpdateRequest request);

    public void deleteClass(String id);
}
