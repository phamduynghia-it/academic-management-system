package com.duynghia.Academic.Management.System.academic.repository;

import com.duynghia.Academic.Management.System.academic.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
