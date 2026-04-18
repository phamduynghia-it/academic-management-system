package com.duynghia.Academic.Management.System.academic.mapper;

import com.duynghia.Academic.Management.System.academic.dto.request.FacultyCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.FacultyUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.FacultyResponse;
import com.duynghia.Academic.Management.System.academic.dto.response.FacultySummaryResponse;
import com.duynghia.Academic.Management.System.academic.entities.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FacultyMapper {
    Faculty toFaculty(FacultyCreationRequest request);

    FacultyResponse toFacultyResponse(Faculty faculty);

    FacultySummaryResponse toFacultySummary(Faculty faculty);

    void updateFacultyFromRequest(@MappingTarget Faculty faculty, FacultyUpdateRequest request);
}
