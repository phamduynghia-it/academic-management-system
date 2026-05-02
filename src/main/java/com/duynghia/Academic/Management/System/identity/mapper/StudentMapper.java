package com.duynghia.Academic.Management.System.identity.mapper;

import com.duynghia.Academic.Management.System.identity.dto.request.StudentProfileUpdateRequest;
import com.duynghia.Academic.Management.System.identity.entities.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "studentClass", ignore = true)
    void updateStudent(@MappingTarget Student student, StudentProfileUpdateRequest request);
}
