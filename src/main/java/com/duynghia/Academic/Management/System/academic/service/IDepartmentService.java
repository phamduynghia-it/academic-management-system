package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.DepartmentCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.DepartmentUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    public DepartmentResponse createDepartment(DepartmentCreationRequest request);

    public void deleteDepartment(String id);

    public DepartmentResponse updateDepartment(String id, DepartmentUpdateRequest request);

    public DepartmentResponse getDepartmentById(String id);

    public List<DepartmentResponse> getAllDepartments();
}
