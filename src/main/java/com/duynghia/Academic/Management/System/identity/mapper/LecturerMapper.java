package com.duynghia.Academic.Management.System.identity.mapper;

import com.duynghia.Academic.Management.System.identity.dto.request.LectureCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.request.LecturerProfileUpdateRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.LectureResponse;
import com.duynghia.Academic.Management.System.identity.entities.Lecturer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LecturerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "department", ignore = true)
    void updateLecturer(@MappingTarget Lecturer lecturer, LecturerProfileUpdateRequest request);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "department", ignore = true)
    Lecturer toLecture(LectureCreationRequest request);

    @Mapping(target = "departmentId", source = "department.departmentId")
    LectureResponse toLectureResponse(Lecturer lecturer);
}
