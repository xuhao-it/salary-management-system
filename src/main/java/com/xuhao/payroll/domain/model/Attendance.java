package com.xuhao.payroll.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Attendance extends BaseEntity {
    private Integer attendanceId;
    private Integer empId;
    private LocalDate attendanceDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Integer status; // 1:正常 2:迟到 3:早退 4:旷工 5:请假
    private String remark;

    // 关联属性
    private Employee employee;
}