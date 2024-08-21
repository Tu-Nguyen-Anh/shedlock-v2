# Sử dụng một image base từ openjdk
FROM openjdk:17-jdk-alpine

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file jar vào container
COPY target/my-application.jar /app/my-application.jar

# Khai báo lệnh chạy khi container khởi động
ENTRYPOINT ["java", "-jar", "/app/my-application.jar"]
