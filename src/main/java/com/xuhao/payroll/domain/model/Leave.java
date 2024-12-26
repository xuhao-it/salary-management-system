package com.xuhao.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Leave extends BaseEntity {
    private Integer leaveId;
    private Integer empId;
    private Integer leaveType; // 1:年假 2:病假 3:事假 4:婚假 5:产假 6:丧假
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal duration;
    private String reason;
    private Integer status; // 1:待审批 2:已批准 3:已驳回
    private Integer approverId;

    // 关联属性
    private Employee employee;
    private Employee approver;
}