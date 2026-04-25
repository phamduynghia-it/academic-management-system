package com.duynghia.Academic.Management.System.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1001, "Lỗi hệ thống không xác định", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1002, "Mã lỗi không hợp lệ", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(1016, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1017, "Chưa xác thực danh tính", HttpStatus.UNAUTHORIZED),
    USERNAME_REQUIRED(1002, "Tên đăng nhập không được để trống", HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED(1003, "Mật khẩu không được để trống", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Mật khẩu phải nằm trong khoảng {min} đến {max} kí tự", HttpStatus.BAD_REQUEST),
    EMAIL_REQUIRED(1005, "Email không được để trống", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1006, "Email không đúng định dạng", HttpStatus.BAD_REQUEST),
    ROLE_REQUIRED(1007, "Vai trò không được để trống", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(1008, "Mật khẩu phải nằm trong khoảng {min} đến {max} kí tự", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1010, "Tên đăng nhập đã tồn tại", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(1009, "Vai trò không tồn tại", HttpStatus.NOT_FOUND),

    PHONE_INVALID_FORMAT(2001, "Số điện thoại không hợp lệ", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID_FORMAT(2002, "Email không đúng định dạng", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID_LENGTH(2003, "Email không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    ADDRESS_INVALID_LENGTH(2004, "Địa chỉ không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    ENGLISH_NAME_INVALID_LENGTH(2005, "Tên tiếng Anh không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),


    FACULTY_ID_REQUIRED(2010, "Mã khoa không được để trống", HttpStatus.BAD_REQUEST),
    FACULTY_ID_INVALID_LENGTH(2011, "Mã khoa không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    FACULTY_NAME_REQUIRED(2012, "Tên khoa không được để trống", HttpStatus.BAD_REQUEST),
    FACULTY_NAME_INVALID_LENGTH(2013, "Tên khoa không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    FACULTY_REQUIRED(2014, "Khoa quản lý không được để trống", HttpStatus.BAD_REQUEST),
    FACULTY_NOT_EXISTED(2015, "Khoa không tồn tại", HttpStatus.NOT_FOUND),


    DEPARTMENT_ID_REQUIRED(2020, "Mã bộ môn không được để trống", HttpStatus.BAD_REQUEST),
    DEPARTMENT_ID_INVALID_LENGTH(2021, "Mã bộ môn không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    DEPARTMENT_NAME_REQUIRED(2022, "Tên bộ môn không được để trống", HttpStatus.BAD_REQUEST),
    DEPARTMENT_NAME_INVALID_LENGTH(2023, "Tên bộ môn không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    HEAD_OF_DEPARTMENT_INVALID_LENGTH(2024, "Mã trưởng bộ môn không vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    DEPARTMENT_EXISTED(2025, "Mã bộ môn đã tồn tại", HttpStatus.BAD_REQUEST),


    CLASS_ID_REQUIRED(2030, "Mã lớp không được để trống", HttpStatus.BAD_REQUEST),
    CLASS_ID_INVALID_LENGTH(2031, "Mã lớp không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    CLASS_NAME_REQUIRED(2032, "Tên lớp không được để trống", HttpStatus.BAD_REQUEST),
    CLASS_NAME_INVALID_LENGTH(2033, "Tên lớp không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    COURSE_YEAR_REQUIRED(2034, "Khóa học không được để trống", HttpStatus.BAD_REQUEST),
    COURSE_YEAR_INVALID_LENGTH(2035, "Khóa học không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),


    STUDENT_ID_REQUIRED(3001, "Mã sinh viên không được để trống", HttpStatus.BAD_REQUEST),
    STUDENT_ID_INVALID_LENGTH(3002, "Mã sinh viên không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    FULL_NAME_INVALID_LENGTH(3003, "Họ tên không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),

    PROGRAM_ID_INVALID_LENGTH(3006, "Mã chương trình đào tạo không vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),

    LECTURER_ID_REQUIRED(3101, "Mã giảng viên không được để trống", HttpStatus.BAD_REQUEST),
    LECTURER_ID_INVALID_LENGTH(3102, "Mã giảng viên không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    ACADEMIC_TITLE_INVALID_LENGTH(3103, "Học hàm không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    DEGREE_INVALID_LENGTH(3104, "Học vị không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    POSITION_INVALID_LENGTH(3105, "Chức danh không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    SECTION_ID_INVALID_LENGTH(3106, "Mã lớp học phần không được vượt quá {max} kí tự", HttpStatus.BAD_REQUEST),


    FULLNAME_REQUIRED(1011, "Họ và tên không được để trống", HttpStatus.BAD_REQUEST),
    PROGRAM_REQUIRED(4026, "Chương trình đào tạo không được để trống", HttpStatus.BAD_REQUEST),
    SEMETER_ID_REQUIRED(4027, "Kỳ học không được để trống", HttpStatus.BAD_REQUEST),
    SECTION_NAME_REQUIRED(4028, "Tên lớp học phần không được để trống", HttpStatus.BAD_REQUEST),
    SEMESTER_REQUIRED(4029, "Kỳ học không được bỏ trống", HttpStatus.BAD_REQUEST),


    CLASS_NOT_FOUND(4001, "Không tìm thấy thông tin lớp học", HttpStatus.NOT_FOUND),
    // --- Series 4000: Lỗi KHÔNG TÌM THẤY (NOT FOUND) ---

    FACULTY_NOT_FOUND(4003, "Không tìm thấy thông tin khoa", HttpStatus.NOT_FOUND),
    PROGRAM_NOT_FOUND(4004, "Không tìm thấy thông tin chương trình đào tạo", HttpStatus.NOT_FOUND),
    COURSE_NOT_FOUND(4005, "Không tìm thấy thông tin học phần", HttpStatus.NOT_FOUND),
    SECTION_NOT_FOUND(4006, "Không tìm thấy thông tin lớp học phần", HttpStatus.NOT_FOUND),
    STUDENT_NOT_FOUND(4007, "Không tìm thấy thông tin hồ sơ sinh viên", HttpStatus.NOT_FOUND),
    LECTURER_NOT_FOUND(4008, "Không tìm thấy thông tin hồ sơ giảng viên", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(4009, "Không tìm thấy thông tin hồ sơ người dùng", HttpStatus.NOT_FOUND),

    // --- Series 4010: Lỗi TRÙNG LẶP DỮ LIỆU (EXISTED / CONFLICT) ---
    PROGRAM_EXISTED(4011, "Mã chương trình đào tạo đã tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),
    COURSE_EXISTED(4012, "Mã học phần đã tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),
    SECTION_EXISTED(4013, "Mã lớp học phần đã được mở trong hệ thống", HttpStatus.BAD_REQUEST),

    // --- Series 4020: Lỗi LOGIC NGHIỆP VỤ (BUSINESS RULE) ---
    COURSE_ALREADY_IN_PROGRAM(4021, "Học phần này đã được thêm vào chương trình đào tạo từ trước", HttpStatus.BAD_REQUEST),
    COURSE_NOT_EXISTED_IN_PROGRAM(4029, "Học phần này không tồn tại trong chương trình đào tạo", HttpStatus.BAD_REQUEST),
    COURSE_ID_REQUIRED(4026, "Mã học phần không được để trống", HttpStatus.BAD_REQUEST),
    CREDITS_REQUIRED(4027, "Số tín chỉ không được để trống", HttpStatus.BAD_REQUEST),
    STUDENT_ALREADY_IN_SECTION(4022, "Sinh viên đã đăng ký lớp học phần này rồi", HttpStatus.BAD_REQUEST),
    PREREQUISITE_NOT_MET(4023, "Sinh viên chưa tích lũy đủ học phần tiên quyết để đăng ký môn này", HttpStatus.BAD_REQUEST),
    SECTION_IS_FULL(4024, "Lớp học phần đã đạt số lượng đăng ký tối đa", HttpStatus.BAD_REQUEST),
    INVALID_SEMESTER(4025, "Học kỳ đăng ký không hợp lệ", HttpStatus.BAD_REQUEST),
    DEPARTMENT_NOT_FOUND(4002, "Không tìm thấy thông tin bộ môn", HttpStatus.NOT_FOUND),
    MAX_CAPACITY_INVALID(4030, "Số lượng sinh viên tối đa không hợp lệ", HttpStatus.BAD_REQUEST);


    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
