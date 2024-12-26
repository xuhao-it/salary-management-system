package com.xuhao.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Reward extends BaseEntity {
    private Integer rewardId;
    private Integer empId;
    private Integer type; // 1:奖励 2:处罚
    private BigDecimal amount;
    private String reason;
    private LocalDate occurDate;

    // 关联属性
    private Employee employee;
}