package com.xuhao.payroll.infrastructure.config.javafx;

import org.springframework.context.ConfigurableApplicationContext;

public class SpringContext {
    private static ConfigurableApplicationContext applicationContext;

    public static void setApplicationContext(ConfigurableApplicationContext context) {
        applicationContext = context;
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
