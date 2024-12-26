package com.xuhao.payroll.mapper;

import com.xuhao.payroll.infrastructure.common.base.BaseMapper;
import com.xuhao.payroll.model.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门Mapper接口
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department, Long> {
    /**
     * 根据部门名称查询
     * 
     * @param deptName 部门名称
     * @return 部门信息
     */
    Department selectByDeptName(String deptName);
}