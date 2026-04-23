package com.duynghia.Academic.Management.System.academic.mapper;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseResponse;
import com.duynghia.Academic.Management.System.academic.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {DepartmentMapper.class})
public interface CourseMapper {

    Course toCourse(CourseCreationRequest request);

    CourseResponse toCourseResponse(Course course);

    void updateCourseFromRequest(@MappingTarget Course course, CourseUpdateRequest request);
}
