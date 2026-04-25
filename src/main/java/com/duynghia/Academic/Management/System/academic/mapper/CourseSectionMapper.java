package com.duynghia.Academic.Management.System.academic.mapper;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseSectionResponse;
import com.duynghia.Academic.Management.System.academic.entities.CourseSection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseSectionMapper {
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "lecturer", ignore = true)
    CourseSection toCourseSection(CourseSectionCreationRequest request);

    @Mapping(source = "course.courseId", target = "courseId")
    @Mapping(source = "course.courseName", target = "courseName")
    @Mapping(source = "course.credits", target = "credits")
    @Mapping(source = "lecturer.lecturerId", target = "lecturerId")
    @Mapping(source = "lecturer.fullName", target = "lecturerName")
    CourseSectionResponse toCourseSectionResponse(CourseSection courseSection);

    void updateCourseSection(@MappingTarget CourseSection courseSection, CourseSectionUpdateRequest request);
}
