package com.xuhao.salary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalaryApiApplication {
    // 建议使用 mvn spring-boot:run 启动应用
    // 这样可以确保正确加载所有Spring Boot配置和依赖
    public static void main(String[] args) {
        SpringApplication.run(SalaryApiApplication.class, args);
    }
}
