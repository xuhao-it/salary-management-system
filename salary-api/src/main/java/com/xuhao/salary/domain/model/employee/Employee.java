package com.xuhao.salary.domain.model.employee;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id", columnDefinition = "INT")
    private Integer empId;  // 改为 Integer 类型

    @Column(name = "emp_no")
    private String empNo;

    @Column(name = "emp_name")
    private String empName;

    private String gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "id_card")
    private String idCard;

    private String phone;
    private String email;
    private String address;

    @Column(name = "dept_id")
    private Long deptId;

    private String position;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "leave_date")
    private LocalDate leaveDate;

    private Integer status;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
