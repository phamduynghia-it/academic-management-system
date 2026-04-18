package com.duynghia.Academic.Management.System.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Quản lý Đào tạo Đại học (Academic Management System)",
                version = "1.0.0",
                description = "Tài liệu API cho hệ thống quản lý điểm và đăng ký tín chỉ",
                contact = @Contact(
                        name = "Phạm Duy Nghĩa",
                        email = "nghia@utc.edu.vn"
                ),
                license = @License(
                        name = "Bản quyền thuộc về UTC"
                )
        ),
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Nhập Token JWT vào đây (Không cần gõ chữ Bearer)",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
