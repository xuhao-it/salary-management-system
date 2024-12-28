package com.xuhao.salary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.xuhao.salary.infrastructure.persistence.mapper")
@EnableTransactionManagement
public class SalaryApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalaryApiApplication.class, args);
    }
}
