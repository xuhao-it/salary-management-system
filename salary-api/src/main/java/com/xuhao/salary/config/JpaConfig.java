package com.xuhao.salary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.xuhao.salary.infrastructure.repository")
@EnableTransactionManagement
public class JpaConfig {
}
