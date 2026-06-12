package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.common.BaseExcelImporter;
import com.duynghia.Academic.Management.System.identity.dto.request.StudentCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.identity.enums.RoleName;
import com.duynghia.Academic.Management.System.identity.service.impl.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentExcelService extends BaseExcelImporter {

    StudentService studentService;


    @Override
    protected void processRow(Row row) throws Exception {
        // 1. Chỉ việc dùng hàm getCellValue được kế thừa từ lớp cha
        String username = getCellValue(row.getCell(0));
        String password = getCellValue(row.getCell(1));
        String email = getCellValue(row.getCell(2));
        String lastName = getCellValue(row.getCell(3));
        String firstName = getCellValue(row.getCell(4));
        String programId = getCellValue(row.getCell(5));
        String classId = getCellValue(row.getCell(6));
        String phone = getCellValue(row.getCell(7));
        String address = getCellValue(row.getCell(8));
        LocalDate dob = parseDateOfBirth(row.getCell(9));
        String cohort = getCellValue(row.getCell(10));

        // 2. Build DTO
        UserCreationRequest userReq = UserCreationRequest.builder()
                .username(username)
                .role(String.valueOf(RoleName.STUDENT))
                .password(password)
                .email(email)
                .build();

        StudentCreationRequest studentReq = StudentCreationRequest.builder()
                .user(userReq)
                .lastName(lastName)
                .firstName(firstName)
                .programId(programId)
                .studentClassId(classId)
                .phoneNumber(phone)
                .address(address)
                .dateOfBirth(dob)
                .cohort(cohort)
                .build();

        // 3. Lưu xuống DB
        studentService.createStudent(studentReq);
    }

    @Override
    protected String getRowIdentifier(Row row) {
        // Cột số 5 là cột Mã SV, lấy ra để lớp cha in báo lỗi
        return getCellValue(row.getCell(0));
    }
}