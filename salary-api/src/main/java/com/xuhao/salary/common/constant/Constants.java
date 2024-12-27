package com.xuhao.payroll.infrastructure.common.constant;

/**
 * 系统常量
 */
public class Constants {
    /** 默认密码 */
    public static final String DEFAULT_PASSWORD = "123456";

    /** 状态：启用 */
    public static final Integer STATUS_ENABLED = 1;
    /** 状态：禁用 */
    public static final Integer STATUS_DISABLED = 0;

    /** 角色类型：系统管理员 */
    public static final Integer ROLE_TYPE_ADMIN = 1;
    /** 角色类型：人事专员 */
    public static final Integer ROLE_TYPE_HR = 2;
    /** 角色类型：财务专员 */
    public static final Integer ROLE_TYPE_FINANCE = 3;
    /** 角色类型：部门主管 */
    public static final Integer ROLE_TYPE_MANAGER = 4;
    /** 角色类型：普通员工 */
    public static final Integer ROLE_TYPE_EMPLOYEE = 5;

    /** 审批状态：待审批 */
    public static final Integer APPROVAL_STATUS_PENDING = 1;
    /** 审批状态：已通过 */
    public static final Integer APPROVAL_STATUS_APPROVED = 2;
    /** 审批状态：已驳回 */
    public static final Integer APPROVAL_STATUS_REJECTED = 3;

    /** 工资状态：未发放 */
    public static final Integer SALARY_STATUS_UNPAID = 1;
    /** 工资状态：已发放 */
    public static final Integer SALARY_STATUS_PAID = 2;

    /** 奖惩类型：奖励 */
    public static final Integer REWARD_TYPE_BONUS = 1;
    /** 奖惩类型：处罚 */
    public static final Integer REWARD_TYPE_PENALTY = 2;

    /** 考勤状态：正常 */
    public static final Integer ATTENDANCE_STATUS_NORMAL = 1;
    /** 考勤状态：迟到 */
    public static final Integer ATTENDANCE_STATUS_LATE = 2;
    /** 考勤状态：早退 */
    public static final Integer ATTENDANCE_STATUS_EARLY = 3;
    /** 考勤状态：旷工 */
    public static final Integer ATTENDANCE_STATUS_ABSENT = 4;
    /** 考勤状态：请假 */
    public static final Integer ATTENDANCE_STATUS_LEAVE = 5;

    /** 请假类型：事假 */
    public static final Integer LEAVE_TYPE_PERSONAL = 1;
    /** 请假类型：病假 */
    public static final Integer LEAVE_TYPE_SICK = 2;
    /** 请假类型：年假 */
    public static final Integer LEAVE_TYPE_ANNUAL = 3;
    /** 请假类型：调休 */
    public static final Integer LEAVE_TYPE_COMPENSATORY = 4;
    /** 请假类型：其他 */
    public static final Integer LEAVE_TYPE_OTHER = 5;
}