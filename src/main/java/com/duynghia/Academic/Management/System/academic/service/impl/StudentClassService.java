package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.StudentClassCreationRequest;
import com.duynghia.Academic.Management.System.academic.entities.Faculty;
import com.duynghia.Academic.Management.System.academic.entities.StudentClass;
import com.duynghia.Academic.Management.System.academic.mapper.ClassMapper;
import com.duynghia.Academic.Management.System.academic.repository.FacultyRepository;
import com.duynghia.Academic.Management.System.academic.repository.StudentClassRepository;
import com.duynghia.Academic.Management.System.academic.service.IStudentClassService;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentClassService implements IStudentClassService {
    StudentClassRepository classRepository;
    ClassMapper classMapper;
    FacultyRepository facultyRepository;

    @Override
    public StudentClass createClass(StudentClassCreationRequest request) {
        StudentClass newClass = classMapper.toClass(request);
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new AppException(ErrorCode.FACULTY_NOT_EXISTED));
        newClass.setFaculty(faculty);
        return classRepository.save(newClass);
    }
}
