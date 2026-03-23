package com.duynghia.Academic.Management.System.academic.repository;

import com.duynghia.Academic.Management.System.academic.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
