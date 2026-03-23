package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.DepartmentCreationRequest;
import com.duynghia.Academic.Management.System.academic.entities.Department;

public interface IDepartmentService {
    public Department createDepartment(DepartmentCreationRequest request);
}
