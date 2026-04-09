package com.duynghia.Academic.Management.System.academic.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "program")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Program {

    @Id
    @Column(name = "program_id", length = 25) // MaCTDT
    @NotBlank(message = "PROGRAM_ID_REQUIRED")
    @Size(max = 25, message = "PROGRAM_ID_INVALID_LENGTH")
    String programId;

    @Column(name = "program_name", columnDefinition = "nvarchar(200)", nullable = false) // TenCTDT
    @NotBlank(message = "PROGRAM_NAME_REQUIRED")
    @Size(max = 200, message = "PROGRAM_NAME_INVALID_LENGTH")
    String programName;

    @Column(name = "english_name", length = 200) // TenCTDTTiengAnh
    @Size(max = 200, message = "ENGLISH_NAME_INVALID_LENGTH")
    String englishName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id") // MaKhoa
    Faculty faculty;

    @Column(name = "applicable_major", columnDefinition = "nvarchar(200)") // NganhApDung
    @Size(max = 200, message = "MAJOR_INVALID_LENGTH")
    String applicableMajor;

    @Column(name = "applicable_cohort", length = 10) // KhoaHocApDung (VD: K64, K65)
    @Size(max = 10, message = "COHORT_INVALID_LENGTH")
    String applicableCohort;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ProgramCourse> programCourses;
}
