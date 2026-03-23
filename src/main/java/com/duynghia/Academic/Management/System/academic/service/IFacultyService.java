package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.FacultyCreationRequest;
import com.duynghia.Academic.Management.System.academic.entities.Faculty;

public interface IFacultyService {
    public Faculty createFaculty(FacultyCreationRequest request);
}
