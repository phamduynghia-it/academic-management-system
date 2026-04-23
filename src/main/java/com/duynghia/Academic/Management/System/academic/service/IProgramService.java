package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCourseRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.ProgramUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramCourseResponse;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramResponse;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramWithCoursesResponse;

import java.util.List;


public interface IProgramService {
    public ProgramResponse createProgram(ProgramCreationRequest request);

    public List<ProgramResponse> getAllPrograms();

    public ProgramResponse getProgramById(String id);

    public ProgramResponse updateProgram(String id, ProgramUpdateRequest request);

    public void deleteProgram(String id);

    public ProgramCourseResponse addCourseToProgram(String programId, ProgramCourseRequest request);

    public ProgramWithCoursesResponse getProgramDetails(String programId);

    public void deleteCourseInProgram(String programId, String courseId);
}
