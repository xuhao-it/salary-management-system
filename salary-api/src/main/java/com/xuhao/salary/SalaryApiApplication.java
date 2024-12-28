package com.xuhao.salary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xuhao.salary.infrastructure.persistence.mapper")
public class SalaryApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalaryApiApplication.class, args);
    }
}
