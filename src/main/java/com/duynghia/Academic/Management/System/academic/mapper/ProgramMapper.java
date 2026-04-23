package com.duynghia.Academic.Management.System.academic.mapper;

import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.ProgramUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramResponse;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramWithCoursesResponse;
import com.duynghia.Academic.Management.System.academic.entities.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ProgramCourseMapper.class, FacultyMapper.class}
)
public interface ProgramMapper {

    Program toProgram(ProgramCreationRequest request);

    ProgramResponse toProgramResponse(Program program);

    void updateProgramFromRequest(@MappingTarget Program program, ProgramUpdateRequest request);

    @Mapping(source = "programCourses", target = "courses")
    ProgramWithCoursesResponse toProgramWithCoursesResponse(Program program);

}
