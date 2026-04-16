package com.duynghia.Academic.Management.System.academic.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentResponse {
    String departmentId;

    String departmentName;

    String englishName;

    String facultyId;

    String headOfDepartment;

    String address;

    String phone;

    String email;
}
