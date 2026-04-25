package com.duynghia.Academic.Management.System.academic.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "registration_period")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "period_id", length = 36)
    String periodId;

    @Column(name = "period_name", columnDefinition = "nvarchar(200)", nullable = false)
    @NotBlank(message = "PERIOD_NAME_REQUIRED")
    String periodName;

    @Column(name = "academic_year", length = 20, nullable = false)
    @NotBlank(message = "ACADEMIC_YEAR_REQUIRED")
    @Size(max = 20, message = "ACADEMIC_YEAR_INVALID_LENGTH")
    String academicYear;

    @Column(name = "semester", nullable = false)
    @NotNull(message = "SEMESTER_REQUIRED")
    Integer semester;

    @Column(name = "phase", nullable = false)
    @NotNull(message = "PHASE_REQUIRED")
    Integer phase;

    @Column(name = "start_time", nullable = false)
    @NotNull(message = "START_TIME_REQUIRED")
    LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    @NotNull(message = "END_TIME_REQUIRED")
    LocalDateTime endTime;

    @Column(name = "is_active", nullable = false)
    @NotNull(message = "IS_ACTIVE_REQUIRED")
    Boolean isActive;
}
