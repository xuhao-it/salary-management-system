package com.xuhao.payroll.service.impl;

import com.xuhao.payroll.model.Salary;
import com.xuhao.payroll.service.SalaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    private static final BigDecimal SOCIAL_INSURANCE_RATE = new BigDecimal("0.105"); // 10.5%
    private static final BigDecimal HOUSING_FUND_RATE = new BigDecimal("0.12"); // 12%

    @Override
    @Transactional
    public Salary calculateSalary(Long employeeId) {
        Salary salary = new Salary();
        salary.setEmployeeId(employeeId);
        salary.setPaymentDate(LocalDateTime.now());

        // 获取基本工资（这里需要从员工信息中获取）
        BigDecimal basicSalary = getEmployeeBasicSalary(employeeId);
        salary.setBasicSalary(basicSalary);

        // 计算岗位工资（这里需要从员工岗位信息中获取）
        BigDecimal positionSalary = calculatePositionSalary(employeeId);
        salary.setPositionSalary(positionSalary);

        // 计算绩效工资（需要从绩效考核系统获取数据）
        BigDecimal performanceSalary = calculatePerformanceSalary(employeeId);
        salary.setPerformanceSalary(performanceSalary);

        // 计算加班工资（需要从考勤系统获取加班数据）
        BigDecimal overtimePay = calculateOvertimePay(employeeId);
        salary.setOvertimePay(overtimePay);

        // 计算各种补贴
        BigDecimal allowance = calculateAllowance(employeeId);
        salary.setAllowance(allowance);

        // 计算应发工资总额
        BigDecimal totalSalary = basicSalary
                .add(positionSalary)
                .add(performanceSalary)
                .add(overtimePay)
                .add(allowance);

        // 计算社保
        BigDecimal socialInsurance = totalSalary.multiply(SOCIAL_INSURANCE_RATE)
                .setScale(2, RoundingMode.HALF_UP);
        salary.setSocialInsurance(socialInsurance);

        // 计算公积金
        BigDecimal housingFund = totalSalary.multiply(HOUSING_FUND_RATE)
                .setScale(2, RoundingMode.HALF_UP);
        salary.setHousingFund(housingFund);

        // 计算个人所得税
        BigDecimal personalIncomeTax = calculatePersonalIncomeTax(totalSalary
                .subtract(socialInsurance)
                .subtract(housingFund));
        salary.setPersonalIncomeTax(personalIncomeTax);

        // 计算实发工资
        BigDecimal netSalary = totalSalary
                .subtract(socialInsurance)
                .subtract(housingFund)
                .subtract(personalIncomeTax);
        salary.setNetSalary(netSalary);

        // 设置工资状态为未发放
        salary.setStatus(0);
        salary.setCreateTime(LocalDateTime.now());
        salary.setUpdateTime(LocalDateTime.now());

        // 保存工资记录
        saveSalary(salary);

        return salary;
    }

    @Override
    public List<Salary> calculateDepartmentSalaries(Long departmentId) {
        // 获取部门所有员工，并为每个员工计算工资
        List<Long> employeeIds = getEmployeesByDepartment(departmentId);
        return employeeIds.stream()
                .map(this::calculateSalary)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean paySalary(Long salaryId) {
        // 更新工资状态为已发放
        Salary salary = getSalaryById(salaryId);
        if (salary != null && salary.getStatus() == 0) {
            salary.setStatus(1);
            salary.setUpdateTime(LocalDateTime.now());
            return updateSalary(salary);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean payBatchSalaries(List<Long> salaryIds) {
        return salaryIds.stream()
                .allMatch(this::paySalary);
    }

    @Override
    public Salary getSalaryById(Long salaryId) {
        // TODO: 从数据库中获取工资记录
        return null;
    }

    @Override
    public List<Salary> getEmployeeSalaryHistory(Long employeeId) {
        // TODO: 从数据库中获取员工工资历史记录
        return Collections.emptyList();
    }

    @Override
    public boolean updateSalary(Salary salary) {
        // TODO: 更新工资记录
        return false;
    }

    @Override
    public boolean deleteSalary(Long salaryId) {
        // TODO: 删除工资记录
        return false;
    }

    // 其他接口实现方法...

    // 私有辅助方法
    private BigDecimal getEmployeeBasicSalary(Long employeeId) {
        // TODO: 从员工信息中获取基本工资
        return BigDecimal.ZERO;
    }

    private BigDecimal calculatePositionSalary(Long employeeId) {
        // TODO: 根据员工岗位计算岗位工资
        return BigDecimal.ZERO;
    }

    private BigDecimal calculatePerformanceSalary(Long employeeId) {
        // TODO: 根据绩效考核结果计算绩效工资
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateOvertimePay(Long employeeId) {
        // TODO: 根据加班记录计算加班工资
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateAllowance(Long employeeId) {
        // TODO: 计算各种补贴
        return BigDecimal.ZERO;
    }

    private BigDecimal calculatePersonalIncomeTax(BigDecimal taxableIncome) {
        // 个税起征点
        BigDecimal threshold = new BigDecimal("5000");

        // 应纳税所得额 = 应纳税所得额 - 起征点
        BigDecimal taxableAmount = taxableIncome.subtract(threshold);

        if (taxableAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        // 2019年最新个税税率表
        if (taxableAmount.compareTo(new BigDecimal("3000")) <= 0) {
            // 不超过3000元的，税率3%
            return taxableAmount.multiply(new BigDecimal("0.03")).setScale(2, RoundingMode.HALF_UP);
        } else if (taxableAmount.compareTo(new BigDecimal("12000")) <= 0) {
            // 3000-12000元的，税率10%
            return taxableAmount.multiply(new BigDecimal("0.10"))
                    .subtract(new BigDecimal("210"))
                    .setScale(2, RoundingMode.HALF_UP);
        } else if (taxableAmount.compareTo(new BigDecimal("25000")) <= 0) {
            // 12000-25000元的，税率20%
            return taxableAmount.multiply(new BigDecimal("0.20"))
                    .subtract(new BigDecimal("1410"))
                    .setScale(2, RoundingMode.HALF_UP);
        } else if (taxableAmount.compareTo(new BigDecimal("35000")) <= 0) {
            // 25000-35000元的，税率25%
            return taxableAmount.multiply(new BigDecimal("0.25"))
                    .subtract(new BigDecimal("2660"))
                    .setScale(2, RoundingMode.HALF_UP);
        } else if (taxableAmount.compareTo(new BigDecimal("55000")) <= 0) {
            // 35000-55000元的，税率30%
            return taxableAmount.multiply(new BigDecimal("0.30"))
                    .subtract(new BigDecimal("4410"))
                    .setScale(2, RoundingMode.HALF_UP);
        } else if (taxableAmount.compareTo(new BigDecimal("80000")) <= 0) {
            // 55000-80000元的，税率35%
            return taxableAmount.multiply(new BigDecimal("0.35"))
                    .subtract(new BigDecimal("7160"))
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            // 超过80000元的，税率45%
            return taxableAmount.multiply(new BigDecimal("0.45"))
                    .subtract(new BigDecimal("15160"))
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }

    private void saveSalary(Salary salary) {
        // TODO: 保存工资记录到数据库
    }

    private List<Long> getEmployeesByDepartment(Long departmentId) {
        // TODO: 获取部门所有员工ID
        return Collections.emptyList();
    }
}