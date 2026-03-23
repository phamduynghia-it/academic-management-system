package com.duynghia.Academic.Management.System.academic.repository;

import com.duynghia.Academic.Management.System.academic.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String> {
}
