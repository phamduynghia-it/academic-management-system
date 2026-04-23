package com.duynghia.Academic.Management.System.academic.mapper;

import com.duynghia.Academic.Management.System.academic.dto.request.DepartmentCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.DepartmentUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.DepartmentResponse;
import com.duynghia.Academic.Management.System.academic.dto.response.DepartmentSummaryResponse;
import com.duynghia.Academic.Management.System.academic.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    Department toDepartment(DepartmentCreationRequest request);

    @Mapping(source = "faculty.facultyId", target = "facultyId")
    DepartmentResponse toDepartmentResponse(Department department);

    DepartmentSummaryResponse toDepartmentSummaryResponse(Department department);

    void updateDepartmentFromRequest(@MappingTarget Department department, DepartmentUpdateRequest request);
}
