package com.duynghia.Academic.Management.System.academic.repository;

import com.duynghia.Academic.Management.System.academic.entities.StudentSemesterCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentSemesterCreditRepository extends JpaRepository<StudentSemesterCredit, String> {
    Optional<StudentSemesterCredit> findByStudent_StudentIdAndAcademicYearAndSemester(String studentId, String academicYear, Integer semester);
}
