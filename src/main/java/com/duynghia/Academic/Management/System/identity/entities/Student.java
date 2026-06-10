package com.duynghia.Academic.Management.System.identity.entities;

import com.duynghia.Academic.Management.System.academic.entities.Program;
import com.duynghia.Academic.Management.System.academic.entities.StudentClass;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "student")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @Column(name = "student_id", length = 10)
    String studentId; // MaSinhVien

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

    @Column(name = "last_name", nullable = false, length = 35)
    String lastName; // HoDem

    @Column(name = "first_name", nullable = false, length = 35)
    String firstName; // Ten

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    Program program; // MaCTDT

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    StudentClass studentClass; // MaLop

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth; // NgaySinh

    @Column(name = "address", length = 250)
    String address; // DiaChi

    @Column(name = "phone_number", length = 15)
    String phoneNumber; // DienThoai

    @Column(name = "cohort", length = 15)
    String cohort; //

}