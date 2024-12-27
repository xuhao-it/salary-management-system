package com.xuhao.payroll.service;

import javafx.scene.Node;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 统计服务接口
 */
public interface StatisticsService {
    /**
     * 获取部门工资分布图表
     * 
     * @param year  年份
     * @param month 月份
     * @return 图表节点
     */
    Node getDepartmentSalaryDistribution(int year, int month);

    /**
     * 获取月度工资趋势图表
     * 
     * @param year 年份
     * @return 图表节点
     */
    Node getMonthlySalaryTrend(int year);

    /**
     * 获取员工工资分布图表
     * 
     * @param year  年份
     * @param month 月份
     * @return 图表节点
     */
    Node getEmployeeSalaryDistribution(int year, int month);

    /**
     * 获取部门平均工资对比图表
     * 
     * @param year  年份
     * @param month 月份
     * @return 图表节点
     */
    Node getDepartmentAvgSalaryComparison(int year, int month);

    /**
     * 获取工资组成分析图表
     * 
     * @param year  年份
     * @param month 月份
     * @return 图表节点
     */
    Node getSalaryComponentsAnalysis(int year, int month);

    /**
     * 获取年度工资趋势图表
     * 
     * @return 图表节点
     */
    Node getYearlySalaryTrend();

    /**
     * 获取部门工资统计数据
     * 
     * @param year  年份
     * @param month 月份
     * @return 统计数据（部门名称 -> 总工资）
     */
    Map<String, BigDecimal> getDepartmentSalaryStats(int year, int month);

    /**
     * 获取月度工资统计数据
     * 
     * @param year 年份
     * @return 统计数据（月份 -> 总工资）
     */
    Map<String, BigDecimal> getMonthlySalaryStats(int year);

    /**
     * 获取工资范围分布统计
     * 
     * @param year  年份
     * @param month 月份
     * @return 统计数据（工资范围 -> 人数）
     */
    Map<String, Integer> getSalaryRangeStats(int year, int month);

    /**
     * 获取部门平均工资统计
     * 
     * @param year  年份
     * @param month 月份
     * @return 统计数据（部门名称 -> 平均工资）
     */
    Map<String, BigDecimal> getDepartmentAvgSalaryStats(int year, int month);

    /**
     * 获取工资组成统计数据
     * 
     * @param year  年份
     * @param month 月份
     * @return 统计数据（员工ID -> Map<组成部分, 金额>）
     */
    Map<String, Map<String, BigDecimal>> getSalaryComponentStats(int year, int month);

    /**
     * 获取年度工资统计数据
     * 
     * @return 统计数据（年份 -> 总工资）
     */
    Map<Integer, BigDecimal> getYearlySalaryStats();
}