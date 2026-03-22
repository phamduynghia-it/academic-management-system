package com.duynghia.Academic.Management.System.identity.exception;

import com.duynghia.Academic.Management.System.identity.common.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Bat toan bo loi chua duoc dinh nghia
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error(e.getMessage(), e);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                .build();
        return ResponseEntity.status(ErrorCode.UNCATEGORIZED_EXCEPTION
                .getHttpStatusCode()).body(apiResponse);
    }

    // Bat cac loi da duoc dinh nghia trong ErrorCode
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<?>> handleAppException(AppException e) {
        log.error(e.getMessage(), e);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(e.getErrorCode().getCode())
                .message(e.getErrorCode().getMessage())
                .build();
        return ResponseEntity.status(e.getErrorCode()
                .getHttpStatusCode()).body(apiResponse);
    }

    //bat loi khong có quyền truy cập
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    // Bat loi validate DTO khong hop le
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        FieldError fieldError = e.getBindingResult().getFieldError();
        Map<String, Object> attributes = Collections.emptyMap();
        if (fieldError != null) {
            String enumKey = fieldError.getDefaultMessage();

            try {
                errorCode = ErrorCode.valueOf(enumKey);
                var constraintViolation = e.getBindingResult()
                        .getAllErrors().getFirst()
                        .unwrap(ConstraintViolation.class);

                attributes = constraintViolation.getConstraintDescriptor().getAttributes();
            } catch (IllegalArgumentException ex) {
                log.warn("Lỗi Validate: Không tìm thấy ErrorCode nào ứng với key {}", enumKey);
            }
        }
        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(mapAttribute(errorCode.getMessage(), attributes))
                .build();
        return ResponseEntity.status(errorCode
                .getHttpStatusCode()).body(apiResponse);

    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            message = message.replace("{" + key + "}", value);
        }
        return message;
    }
}