package com.xuhao.salary.interfaces.controller;

import com.xuhao.salary.application.dto.request.employee.CreateEmployeeRequest;
import com.xuhao.salary.application.dto.request.employee.UpdateEmployeeRequest;
import com.xuhao.salary.application.dto.response.employee.EmployeeDetailResponse;
import com.xuhao.salary.application.dto.response.employee.EmployeeListResponse;
import com.xuhao.salary.application.service.interfaces.EmployeeApplicationService;
import com.xuhao.salary.common.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final EmployeeApplicationService employeeApplicationService;

    @PostMapping
    public Result<Void> createEmployee(@RequestBody CreateEmployeeRequest request) {
        employeeApplicationService.createEmployee(request);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeRequest request) {
        request.setId(id);
        employeeApplicationService.updateEmployee(request);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<EmployeeDetailResponse> getEmployee(@PathVariable Long id) {
        return Result.success(employeeApplicationService.getEmployeeDetail(id));
    }

    @GetMapping
    public Result<EmployeeListResponse> listEmployees(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(employeeApplicationService.getEmployeeList(page, size));
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteEmployee(@PathVariable Long id) {
        employeeApplicationService.deleteEmployee(id);
        return Result.success();
    }
}
