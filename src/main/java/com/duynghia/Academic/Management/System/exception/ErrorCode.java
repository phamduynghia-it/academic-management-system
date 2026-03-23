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
    USERNAME_REQUIRED(1002, "Tên đăng nhập không được để trống", HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED(1003, "Mật khẩu không được để trống", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_SHORT(1004, "Mật khẩu tối thiểu {min} ký tự", HttpStatus.BAD_REQUEST),
    EMAIL_REQUIRED(1005, "Email không được để trống", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1006, "Email không đúng định dạng", HttpStatus.BAD_REQUEST),
    ROLE_REQUIRED(1007, "Vai trò không được để trống", HttpStatus.BAD_REQUEST),
    USERNAME_TOO_SHORT(1008, "tên đăng nhập tối thiểu {min} ký tự", HttpStatus.BAD_REQUEST),
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


    CLASS_ID_REQUIRED(2030, "Mã lớp không được để trống", HttpStatus.BAD_REQUEST),
    CLASS_ID_INVALID_LENGTH(2031, "Mã lớp không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    CLASS_NAME_REQUIRED(2032, "Tên lớp không được để trống", HttpStatus.BAD_REQUEST),
    CLASS_NAME_INVALID_LENGTH(2033, "Tên lớp không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    COURSE_YEAR_REQUIRED(2034, "Khóa học không được để trống", HttpStatus.BAD_REQUEST),
    COURSE_YEAR_INVALID_LENGTH(2035, "Khóa học không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST);


    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
