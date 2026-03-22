package com.duynghia.Academic.Management.System.identity.exception;

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
    ROLE_NOT_EXISTED(1009, "Vai trò không tồn tại", HttpStatus.NOT_FOUND);
    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
