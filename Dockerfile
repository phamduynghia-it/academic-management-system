# ==========================================
# GIAI ĐOẠN 1: Tải thư viện và Build source code
# ==========================================
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copy file pom.xml vào trước để tải dependencies
# Bước này giúp Docker "cache" lại các thư viện. Nếu bạn chỉ sửa code mà không sửa pom.xml,
# Docker sẽ bỏ qua bước tải lại mạng, giúp thời gian build nhanh hơn rất nhiều.
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy toàn bộ mã nguồn vào và build ra file .jar
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================================
# GIAI ĐOẠN 2: Đóng gói và Chạy ứng dụng
# ==========================================
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Lấy file .jar đã được build thành công từ giai đoạn 'builder' chuyển sang đây
# Dấu * giúp tự động nhận diện tên file .jar sinh ra trong thư mục target/
COPY --from=builder /app/target/*.jar app.jar

# Mở port cho ứng dụng Spring Boot (mặc định 8080)
EXPOSE 8080

# Lệnh khởi chạy
ENTRYPOINT ["java", "-jar", "app.jar"]