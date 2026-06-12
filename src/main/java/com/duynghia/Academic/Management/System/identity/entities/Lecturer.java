package com.duynghia.Academic.Management.System.identity.entities;

import com.duynghia.Academic.Management.System.academic.entities.Department;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "lecturer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lecturer {

    @Id
    @Column(name = "lecturer_id", length = 10)
    String lecturerId; // MaGiangVien

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    User user;

    @Column(name = "last_name", nullable = false, length = 35)
    String lastName; // HoDem

    @Column(name = "first_name", nullable = false, length = 35)
    String firstName; // Ten

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    Department department; // MaBoMon

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth; // NgaySinh

    @Column(name = "academic_title", columnDefinition = "nvarchar(20)")
    String academicTitle; // HocHam

    @Column(name = "degree", columnDefinition = "nvarchar(20)")
    String degree; // HocVi

    @Column(name = "position", columnDefinition = "nvarchar(20)")
    String position; // ChucDanh

    @Column(name = "phone_number", length = 15)
    String phoneNumber; // DienThoai
    @Column(name = "address", columnDefinition = "nvarchar(100)")
    String address; // DiaChi
}