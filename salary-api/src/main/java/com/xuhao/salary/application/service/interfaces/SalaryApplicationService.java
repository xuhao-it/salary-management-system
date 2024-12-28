package com.xuhao.salary.application.service.interfaces;

import com.xuhao.salary.application.dto.request.salary.CalculateSalaryRequest;
import com.xuhao.salary.application.dto.request.salary.PayrollRequest;
import com.xuhao.salary.application.dto.response.salary.PayrollSummaryResponse;
import com.xuhao.salary.application.dto.response.salary.SalaryDetailResponse;

public interface SalaryApplicationService {
    SalaryDetailResponse calculateSalary(CalculateSalaryRequest request);
    PayrollSummaryResponse processPayroll(PayrollRequest request);
    SalaryDetailResponse getEmployeeSalary(Long employeeId);
}
