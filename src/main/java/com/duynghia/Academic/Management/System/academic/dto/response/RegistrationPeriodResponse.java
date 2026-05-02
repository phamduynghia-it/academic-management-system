package com.duynghia.Academic.Management.System.academic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationPeriodResponse {

    String periodName;

    String academicYear;

    Integer semester;

    Integer phase;

    LocalDateTime startTime;

    LocalDateTime endTime;

    String targetCohort;
}
