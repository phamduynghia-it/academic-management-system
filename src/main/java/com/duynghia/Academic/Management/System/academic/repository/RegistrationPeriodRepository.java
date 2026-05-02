package com.duynghia.Academic.Management.System.academic.repository;

import com.duynghia.Academic.Management.System.academic.entities.RegistrationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface RegistrationPeriodRepository extends JpaRepository<RegistrationPeriod, String> {
    @Query("""
                SELECT COUNT(rp) > 0 FROM RegistrationPeriod rp 
                WHERE rp.isActive = true 
                AND :currentTime BETWEEN rp.startTime AND rp.endTime
                AND (rp.targetCohort IS NULL OR rp.targetCohort = :cohort)
            """)
    boolean existsActivePeriodForStudent(
            @Param("cohort") String cohort,
            @Param("currentTime") LocalDateTime currentTime
    );
}
