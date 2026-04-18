package com.duynghia.Academic.Management.System.academic.mapper;

import com.duynghia.Academic.Management.System.academic.dto.request.StudentClassCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.StudentClassUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.StudentClassResponse;
import com.duynghia.Academic.Management.System.academic.entities.StudentClass;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {FacultyMapper.class})
public interface ClassMapper {
    StudentClass toClass(StudentClassCreationRequest request);

    StudentClassResponse toStudentClassResponse(StudentClass studentClass);

    void updateStudentClassFromRequest(@MappingTarget StudentClass studentClass, StudentClassUpdateRequest request);
}
