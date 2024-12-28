package com.xuhao.salary.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xuhao.salary.infrastructure.persistence.mapper")
public class MyBatisConfig {
    // 配置将在 application.yml 中完成
}
