package com.xuhao.payroll.infrastructure.common.constant;

/**
 * 状态枚举
 */
public enum StatusEnum {
    // 通用状态
    ENABLE(1, "启用"),
    DISABLE(2, "禁用"),

    // 员工状态
    ON_JOB(1, "在职"),
    LEAVE_JOB(2, "离职"),
    PROBATION(3, "试用期"),

    // 审批状态
    PENDING(1, "待审批"),
    APPROVED(2, "已批准"),
    REJECTED(3, "已驳回"),

    // 考勤状态
    NORMAL(1, "正常"),
    LATE(2, "迟到"),
    EARLY_LEAVE(3, "早退"),
    ABSENT(4, "旷工"),
    LEAVE(5, "请假"),

    // 工资状态
    UNPAID(1, "未发放"),
    PAID(2, "已发放");

    private final int code;
    private final String desc;

    StatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static StatusEnum getByCode(int code) {
        for (StatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }
}