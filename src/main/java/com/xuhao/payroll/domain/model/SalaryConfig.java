package com.xuhao.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SalaryConfig extends BaseEntity {
    private Integer configId;
    private Integer empId;
    private BigDecimal basicSalary;
    private BigDecimal positionSalary;
    private BigDecimal insuranceBase;
    private BigDecimal housingFundBase;
    private LocalDate effectiveDate;

    // 关联属性
    private Employee employee;
}