package com.duynghia.Academic.Management.System.academic.mapper;

import com.duynghia.Academic.Management.System.academic.dto.response.CourseInProgramResponse;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramCourseResponse;
import com.duynghia.Academic.Management.System.academic.entities.ProgramCourse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramCourseMapper {
    @Mapping(source = "program.programId", target = "programId")
    @Mapping(source = "program.programName", target = "programName")
    @Mapping(source = "course.courseId", target = "courseId")
    @Mapping(source = "course.courseName", target = "courseName")
    @Mapping(source = "course.credits", target = "credits")
    ProgramCourseResponse toResponse(ProgramCourse programCourse);
    
    @Mapping(source = "course.courseId", target = "courseId")
    @Mapping(source = "course.courseName", target = "courseName")
    @Mapping(source = "course.credits", target = "credits")
    @Mapping(source = "semester", target = "semester")
    CourseInProgramResponse toCourseInProgramResponse(ProgramCourse programCourse);
}
