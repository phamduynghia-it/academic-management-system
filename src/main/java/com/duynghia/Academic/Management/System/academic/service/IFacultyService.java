package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.FacultyCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.FacultyUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.FacultyResponse;

import java.util.List;

public interface IFacultyService {
    public FacultyResponse createFaculty(FacultyCreationRequest request);

    public List<FacultyResponse> getAllFaculties();

    public FacultyResponse getFacultyById(String id);

    public void deleteFaculty(String id);

    public FacultyResponse updateFaculty(String id, FacultyUpdateRequest request);
}
