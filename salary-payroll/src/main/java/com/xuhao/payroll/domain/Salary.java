package com.xuhao.salary.domain.model.salary;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "salary_record")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;
    
    @Column(name = "emp_id")
    private Long empId;
    
    @Column(name = "base_salary")
    private BigDecimal baseSalary;
    
    private BigDecimal bonus;
    private BigDecimal deduction;
    
    @Column(name = "actual_salary")
    private BigDecimal actualSalary;
    
    @Column(name = "salary_month")
    private String salaryMonth;
    
    private Integer status;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
