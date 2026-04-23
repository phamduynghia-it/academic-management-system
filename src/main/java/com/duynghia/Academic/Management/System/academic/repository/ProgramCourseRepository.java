package com.duynghia.Academic.Management.System.academic.repository;

import com.duynghia.Academic.Management.System.academic.entities.ProgramCourse;
import com.duynghia.Academic.Management.System.academic.entities.ProgramCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramCourseRepository extends JpaRepository<ProgramCourse, ProgramCourseId> {

}
