package com.xuhao.payroll.mapper;

import java.util.List;

import com.xuhao.payroll.infrastructure.common.base.BaseMapper;
import com.xuhao.payroll.model.SalaryConfig;

/**
 * 工资配置Mapper接口
 */
public interface SalaryConfigMapper extends BaseMapper<SalaryConfig, Integer> {
    /**
     * 根据员工ID查询
     * 
     * @param empId 员工ID
     * @return 工资配置列表
     */
    List<SalaryConfig> selectByEmpId(Integer empId);

    /**
     * 查询员工最新的工资配置
     * 
     * @param empId 员工ID
     * @return 工资配置信息
     */
    SalaryConfig selectLatestByEmpId(Integer empId);

    /**
     * 查询带员工信息的工资配置列表
     * 
     * @return 工资配置列表（包含员工信息）
     */
    List<SalaryConfig> selectWithEmployee();
}