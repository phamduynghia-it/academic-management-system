package com.duynghia.Academic.Management.System.identity.mapper;

import com.duynghia.Academic.Management.System.identity.dto.request.StudentCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.request.StudentProfileUpdateRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.StudentResponse;
import com.duynghia.Academic.Management.System.identity.entities.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "studentClass", ignore = true)
    @Mapping(target = "program", ignore = true)
    @Mapping(target = "user", ignore = true)
    Student toStudent(StudentCreationRequest request);

    @Mapping(target = "programId", source = "program.programId")
    @Mapping(target = "studentClassId", source = "studentClass.classId")
    StudentResponse toStudentResponse(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "studentClass", ignore = true)
    void updateStudent(@MappingTarget Student student, StudentProfileUpdateRequest request);
}
