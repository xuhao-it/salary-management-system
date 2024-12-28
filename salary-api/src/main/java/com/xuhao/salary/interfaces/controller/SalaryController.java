package com.xuhao.salary.interfaces.controller;

import com.xuhao.salary.application.dto.request.salary.CalculateSalaryRequest;
import com.xuhao.salary.application.dto.request.salary.PayrollRequest;
import com.xuhao.salary.application.dto.response.salary.PayrollSummaryResponse;
import com.xuhao.salary.application.dto.response.salary.SalaryDetailResponse;
import com.xuhao.salary.application.service.interfaces.SalaryApplicationService;
import com.xuhao.salary.common.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salaries")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryApplicationService salaryApplicationService;

    @PostMapping("/calculate")
    public Result<SalaryDetailResponse> calculateSalary(@RequestBody CalculateSalaryRequest request) {
        return Result.success(salaryApplicationService.calculateSalary(request));
    }

    @PostMapping("/payroll")
    public Result<PayrollSummaryResponse> processPayroll(@RequestBody PayrollRequest request) {
        return Result.success(salaryApplicationService.processPayroll(request));
    }

    @GetMapping("/employees/{employeeId}")
    public Result<SalaryDetailResponse> getEmployeeSalary(@PathVariable Long employeeId) {
        return Result.success(salaryApplicationService.getEmployeeSalary(employeeId));
    }
}
