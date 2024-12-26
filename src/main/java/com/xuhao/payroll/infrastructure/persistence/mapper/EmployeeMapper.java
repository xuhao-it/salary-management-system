package com.xuhao.payroll.mapper;

import java.util.List;

import com.xuhao.payroll.infrastructure.common.base.BaseMapper;
import com.xuhao.payroll.model.Employee;

/**
 * 员工Mapper接口
 */
public interface EmployeeMapper extends BaseMapper<Employee, Integer> {
    /**
     * 根据工号查询
     * 
     * @param empNo 工号
     * @return 员工信息
     */
    Employee selectByEmpNo(String empNo);

    /**
     * 根据部门ID查询
     * 
     * @param deptId 部门ID
     * @return 员工列表
     */
    List<Employee> selectByDeptId(Integer deptId);

    /**
     * 根据状态查询
     * 
     * @param status 状态
     * @return 员工列表
     */
    List<Employee> selectByStatus(Integer status);

    /**
     * 查询带部门信息的员工列表
     * 
     * @return 员工列表（包含部门信息）
     */
    List<Employee> selectWithDept();
}