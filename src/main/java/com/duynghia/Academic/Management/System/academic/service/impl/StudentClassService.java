package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.StudentClassCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.StudentClassUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.StudentClassResponse;
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

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentClassService implements IStudentClassService {
    StudentClassRepository classRepository;
    ClassMapper classMapper;
    FacultyRepository facultyRepository;

    @Override
    public StudentClassResponse createClass(StudentClassCreationRequest request) {
        StudentClass newClass = classMapper.toClass(request);
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new AppException(ErrorCode.FACULTY_NOT_EXISTED));
        newClass.setFaculty(faculty);
        return classMapper.toStudentClassResponse(classRepository.save(newClass));
    }


    @Override
    public List<StudentClassResponse> getAllClasses() {
        return classRepository.findAll()
                .stream()
                .map(classMapper::toStudentClassResponse)
                .toList();
    }

    @Override
    public StudentClassResponse getClassById(String id) {
        return classRepository.findById(id)
                .map(classMapper::toStudentClassResponse)
                .orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_FOUND));
    }

    @Override
    public StudentClassResponse updateClass(String id, StudentClassUpdateRequest request) {
        StudentClass studentClass = classRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_FOUND));


        classMapper.updateStudentClassFromRequest(studentClass, request);

        return classMapper.toStudentClassResponse(classRepository.save(studentClass));
    }

    @Override
    public void deleteClass(String id) {
        if (!classRepository.existsById(id)) {
            throw new AppException(ErrorCode.CLASS_NOT_FOUND);
        }
        classRepository.deleteById(id);
    }
}
