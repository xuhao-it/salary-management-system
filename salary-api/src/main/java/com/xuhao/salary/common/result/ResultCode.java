package com.xuhao.payroll.infrastructure.common.result;

/**
 * 系统返回码枚举
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    USER_NOT_EXIST(1001, "用户不存在"),
    LOGIN_FAILED(1002, "用户名或密码错误"),
    USER_DISABLED(1003, "用户已被禁用"),
    TOKEN_INVALID(1004, "无效的令牌"),
    TOKEN_EXPIRED(1005, "令牌已过期"),
    UNAUTHORIZED(1006, "未授权的访问"),
    FORBIDDEN(1007, "禁止访问"),
    PARAM_ERROR(1008, "参数错误"),
    DATA_NOT_FOUND(1009, "数据不存在"),
    DATA_DUPLICATE(1010, "数据重复"),
    FILE_UPLOAD_ERROR(1011, "文件上传失败"),
    EXPORT_ERROR(1012, "导出失败"),
    IMPORT_ERROR(1013, "导入失败");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}