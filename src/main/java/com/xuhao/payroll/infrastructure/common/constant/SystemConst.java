package com.xuhao.payroll.infrastructure.common.constant;

/**
 * 系统常量
 */
public class SystemConst {
    // 系统配置
    public static final String SYSTEM_NAME = "企业工资管理系统";
    public static final String SYSTEM_VERSION = "1.0.0";

    // 用户相关
    public static final String DEFAULT_PASSWORD = "123456";
    public static final String ADMIN_USERNAME = "admin";

    // 角色类型
    public static final int ROLE_SUPER_ADMIN = 1;
    public static final int ROLE_ADMIN = 2;
    public static final int ROLE_HR = 3;
    public static final int ROLE_FINANCE = 4;
    public static final int ROLE_USER = 5;

    // 分页配置
    public static final int DEFAULT_PAGE_SIZE = 10;

    // 文件路径
    public static final String UPLOAD_PATH = "upload/";
    public static final String EXPORT_PATH = "export/";

    // 日期格式
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // 工资相关
    public static final double DEFAULT_INSURANCE_RATE = 0.08;
    public static final double DEFAULT_HOUSING_FUND_RATE = 0.12;
    public static final double DEFAULT_TAX_RATE = 0.03;

    private SystemConst() {
        throw new IllegalStateException("Constant class");
    }
}