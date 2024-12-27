package com.xuhao.payroll.service;

import com.xuhao.payroll.model.Salary;
import java.util.List;

public interface SalaryService {
    // 计算员工工资
    Salary calculateSalary(Long employeeId);

    // 批量计算部门工资
    List<Salary> calculateDepartmentSalaries(Long departmentId);

    // 发放工资
    boolean paySalary(Long salaryId);

    // 批量发放工资
    boolean payBatchSalaries(List<Long> salaryIds);

    // 查询工资记录
    Salary getSalaryById(Long salaryId);

    // 查询员工工资历史
    List<Salary> getEmployeeSalaryHistory(Long employeeId);

    // 更新工资记录
    boolean updateSalary(Salary salary);

    // 删除工资记录
    boolean deleteSalary(Long salaryId);
}