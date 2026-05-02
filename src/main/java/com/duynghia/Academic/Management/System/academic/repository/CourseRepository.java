package com.duynghia.Academic.Management.System.academic.repository;

import com.duynghia.Academic.Management.System.academic.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, String> {
    @Query("""
            SELECT c from Course c
            where (:keyword is null
             or lower(c.courseId) like lower(concat('%', :keyword, '%'))
             or lower(c.courseName) like lower(concat('%', :keyword, '%'))
             )
             AND (:departmentId IS NULL OR c.department.departmentId = :departmentId)
            """)
    Page<Course> seacrhAndFilterCourse(@Param("keyword") String keyword, @Param("departmentId") String departmentId, Pageable pageable);
}
