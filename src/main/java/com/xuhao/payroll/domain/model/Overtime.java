package com.xuhao.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Overtime extends BaseEntity {
    private Integer overtimeId;
    private Integer empId;
    private LocalDate overtimeDate;
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