package com.duynghia.Academic.Management.System.identity.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {
    String studentId;

    String lastName; // HoDem

    String firstName; // Ten

    String programId; // MaCTDT

    String studentClassId; // MaLop

    LocalDate dateOfBirth; // NgaySinh

    String address; // DiaChi

    String phoneNumber; // DienThoai

    String cohort;
}
