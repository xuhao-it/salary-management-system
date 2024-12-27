package com.xuhao.payroll.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 工资数据访问接口
 */
@Mapper
public interface SalaryMapper {
    /**
     * 获取部门工资统计
     */
    Map<String, BigDecimal> getDepartmentSalaryStats(@Param("year") int year, @Param("month") int month);

    /**
     * 获取月度工资统计
     */
    Map<String, BigDecimal> getMonthlySalaryStats(@Param("year") int year);

    /**
     * 获取指定月份所有工资
     */
    List<BigDecimal> getAllSalaries(@Param("year") int year, @Param("month") int month);

    /**
     * 获取部门平均工资统计
     */
    Map<String, BigDecimal> getDepartmentAvgSalaryStats(@Param("year") int year, @Param("month") int month);

    /**
     * 获取工资组成统计
     */
    Map<String, Map<String, BigDecimal>> getSalaryComponentStats(@Param("year") int year, @Param("month") int month);

    /**
     * 获取年度工资统计
     */
    Map<Integer, BigDecimal> getYearlySalaryStats();
}