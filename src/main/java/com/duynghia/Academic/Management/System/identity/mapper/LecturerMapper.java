package com.duynghia.Academic.Management.System.identity.mapper;

import com.duynghia.Academic.Management.System.identity.dto.request.LecturerProfileUpdateRequest;
import com.duynghia.Academic.Management.System.identity.entities.Lecturer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LecturerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "department", ignore = true)
    void updateLecturer(@MappingTarget Lecturer lecturer, LecturerProfileUpdateRequest request);
}
