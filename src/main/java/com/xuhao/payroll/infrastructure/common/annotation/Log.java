package com.xuhao.payroll.infrastructure.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 操作描述
     */
    String value() default "";

    /**
     * 是否记录请求参数
     */
    boolean recordParams() default true;
}