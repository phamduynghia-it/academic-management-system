package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.DepartmentCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.DepartmentUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.DepartmentResponse;
import com.duynghia.Academic.Management.System.academic.entities.Department;
import com.duynghia.Academic.Management.System.academic.entities.Faculty;
import com.duynghia.Academic.Management.System.academic.mapper.DepartmentMapper;
import com.duynghia.Academic.Management.System.academic.repository.DepartmentRepository;
import com.duynghia.Academic.Management.System.academic.repository.FacultyRepository;
import com.duynghia.Academic.Management.System.academic.service.IDepartmentService;
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
public class DepartmentService implements IDepartmentService {
    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;
    FacultyRepository facultyRepository;

    @Override
    public DepartmentResponse createDepartment(DepartmentCreationRequest request) {
        if (departmentRepository.existsById(request.getDepartmentId())) {
            throw new AppException(ErrorCode.DEPARTMENT_EXISTED);
        }
        Department department = departmentMapper.toDepartment(request);
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new AppException(ErrorCode.FACULTY_NOT_EXISTED));
        department.setFaculty(faculty);
        return departmentMapper.toDepartmentResponse(departmentRepository.save(department));
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDepartmentResponse)
                .toList();
    }


    @Override
    public DepartmentResponse getDepartmentById(String id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDepartmentResponse)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
    }


    @Override
    public DepartmentResponse updateDepartment(String id, DepartmentUpdateRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
        departmentMapper.updateDepartmentFromRequest(department, request);

        return departmentMapper.toDepartmentResponse(departmentRepository.save(department));
    }

    @Override
    public void deleteDepartment(String id) {
        if (!departmentRepository.existsById(id)) {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_FOUND);
        }
        departmentRepository.deleteById(id);
    }
}
