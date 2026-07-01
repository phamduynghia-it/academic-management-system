package com.duynghia.Academic.Management.System.academic.repository;

import com.duynghia.Academic.Management.System.academic.entities.CourseSectionStudent;
import com.duynghia.Academic.Management.System.academic.entities.CourseSectionStudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<CourseSectionStudent, CourseSectionStudentId> {
    @Query("""
                SELECT css.courseSection.course.courseId 
                FROM CourseSectionStudent css 
                WHERE css.student.studentId = :studentId 
                AND css.status IN ('PASSED', 'REGISTERED')
            """)
    List<String> findPassedOrRegisteredCourseIds(@Param("studentId") String studentId);

    @Query("""
    SELECT css.courseSection.course.courseId 
    FROM CourseSectionStudent css 
    WHERE css.student.studentId = :studentId 
    AND css.status = 'PASSED'
""")
    List<String> findPassedCourseIds(@Param("studentId") String studentId);

}
