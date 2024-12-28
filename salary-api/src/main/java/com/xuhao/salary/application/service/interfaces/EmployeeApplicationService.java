package com.xuhao.salary.application.service.interfaces;

import com.xuhao.salary.application.dto.request.employee.CreateEmployeeRequest;
import com.xuhao.salary.application.dto.request.employee.UpdateEmployeeRequest;
import com.xuhao.salary.application.dto.response.employee.EmployeeDetailResponse;
import com.xuhao.salary.application.dto.response.employee.EmployeeListResponse;

public interface EmployeeApplicationService {
    void createEmployee(CreateEmployeeRequest request);
    void updateEmployee(UpdateEmployeeRequest request);
    EmployeeDetailResponse getEmployeeDetail(Long id);
    EmployeeListResponse getEmployeeList(Integer page, Integer size);
    void deleteEmployee(Long id);
}
