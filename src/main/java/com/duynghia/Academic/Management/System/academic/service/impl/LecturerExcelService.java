package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.common.BaseExcelImporter;
import com.duynghia.Academic.Management.System.identity.dto.request.LectureCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.identity.enums.RoleName;
import com.duynghia.Academic.Management.System.identity.service.ILectureService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LecturerExcelService extends BaseExcelImporter {
    ILectureService lectureService;

    @Override
    protected void processRow(Row row) throws Exception {
        String username = getCellValue(row.getCell(0));
        String password = getCellValue(row.getCell(1));
        String email = getCellValue(row.getCell(2));
        String lastName = getCellValue(row.getCell(3));
        String firstName = getCellValue(row.getCell(4));
        String departmentId = getCellValue(row.getCell(5));
        String academicTitle = getCellValue(row.getCell(6));
        String degree = getCellValue(row.getCell(7));
        String position = getCellValue(row.getCell(8));
        String phone = getCellValue(row.getCell(9));
        String address = getCellValue(row.getCell(10));
        LocalDate dob = parseDateOfBirth(row.getCell(11));

        UserCreationRequest userReq = UserCreationRequest.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(String.valueOf(RoleName.LECTURER))
                .build();

        LectureCreationRequest lecturerReq = LectureCreationRequest.builder()
                .user(userReq)
                .departmentId(departmentId)
                .academicTitle(academicTitle)
                .degree(degree)
                .position(position)
                .lastName(lastName)
                .firstName(firstName)
                .dateOfBirth(dob)
                .address(address)
                .phoneNumber(phone)
                .build();

        lectureService.createLecture(lecturerReq);
    }

    @Override
    protected String getRowIdentifier(Row row) {
        // Cột số 5 là cột Mã SV, lấy ra để lớp cha in báo lỗi
        return getCellValue(row.getCell(0));
    }
}
