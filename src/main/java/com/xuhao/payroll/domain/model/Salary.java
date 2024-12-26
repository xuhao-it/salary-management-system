package com.xuhao.payroll.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Salary {
    private Long id;
    private Long employeeId;  // 员工ID
    private Integer year;
    private Integer month;
    private LocalDateTime paymentDate;
    private BigDecimal basicSalary;
    private BigDecimal positionSalary;
    private BigDecimal performanceSalary;
    private BigDecimal overtimePay;
    private BigDecimal allowance;
    private BigDecimal bonus;
    private BigDecimal socialInsurance;  // 社会保险
    private BigDecimal housingFund;
    private BigDecimal personalIncomeTax;  // 个人所得税
    private BigDecimal deduction;  // 扣除项
    private BigDecimal netSalary;
    private Integer status;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}