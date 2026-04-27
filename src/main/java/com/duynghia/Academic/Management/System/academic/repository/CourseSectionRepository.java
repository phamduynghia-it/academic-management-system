package com.duynghia.Academic.Management.System.academic.repository;

import com.duynghia.Academic.Management.System.academic.entities.CourseSection;
import com.duynghia.Academic.Management.System.academic.enums.CourseSectionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface CourseSectionRepository extends JpaRepository<CourseSection, String> {
    @Query("""
            SELECT cs FROM CourseSection cs
            WHERE (
                :keyword IS NULL
                OR LOWER(cs.sectionName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                OR LOWER(cs.sectionId) LIKE LOWER(CONCAT('%', :keyword, '%'))
            )    
            AND (:academicYear IS NULL OR cs.academicYear = :academicYear)
            AND (:semester IS NULL OR cs.semester = :semester)
            AND (:phase IS NULL OR cs.phase = :phase)
            AND (:status IS NULL OR cs.status = :status)
            """)
    Page<CourseSection> searchAndFilterCourseSection(
            @Param("keyword") String keyword,
            @Param("academicYear") String academicYear,
            @Param("semester") Integer semester,
            @Param("phase") Integer phase,
            @Param("status") CourseSectionStatus status,
            Pageable pageable
    );

    @Modifying
    @Query("UPDATE CourseSection cs SET cs.status = :newStatus WHERE cs.status = :oldStatus AND cs.startDate <= :today")
    int updateStatusToOngoing(
            @Param("newStatus") CourseSectionStatus newStatus,
            @Param("oldStatus") CourseSectionStatus oldStatus,
            @Param("today") LocalDate today
    );

    @Modifying
    @Query("UPDATE CourseSection cs SET cs.status = :newStatus WHERE cs.status = :oldStatus AND cs.endDate < :today")
    int updateStatusToCompleted(
            @Param("newStatus") CourseSectionStatus newStatus,
            @Param("oldStatus") CourseSectionStatus oldStatus,
            @Param("today") LocalDate today
    );
}
