package com.duynghia.Academic.Management.System.academic.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProgramResponse {
    String programId;
    String programName;
    String englishName;
    String applicableMajor;
    String applicableCohort;
    
    FacultySummaryResponse faculty;
}