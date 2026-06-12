package com.duynghia.Academic.Management.System.identity.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LectureResponse {
    String departmentId; // MaBoMon

    String academicTitle; // HocHam

    String degree; // HocVi

    String position; // ChucDanh

    String lastName; // HoDem

    String firstName; // Ten

    LocalDate dateOfBirth; // NgaySinh

    String address; // DiaChi

    String phoneNumber; // DienThoai
}
