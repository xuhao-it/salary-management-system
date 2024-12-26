package com.xuhao.payroll.service.impl;

import com.xuhao.payroll.infrastructure.common.util.ChartUtil;
import com.xuhao.payroll.mapper.SalaryMapper;
import com.xuhao.payroll.service.StatisticsService;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 统计服务实现类
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public Node getDepartmentSalaryDistribution(int year, int month) {
        Map<String, BigDecimal> stats = getDepartmentSalaryStats(year, month);
        return ChartUtil.createDepartmentSalaryPieChart(stats);
    }

    @Override
    public Node getMonthlySalaryTrend(int year) {
        Map<String, BigDecimal> stats = getMonthlySalaryStats(year);
        return ChartUtil.createMonthlySalaryLineChart(stats);
    }

    @Override
    public Node getEmployeeSalaryDistribution(int year, int month) {
        Map<String, Integer> stats = getSalaryRangeStats(year, month);
        return ChartUtil.createSalaryDistributionBarChart(stats);
    }

    @Override
    public Node getDepartmentAvgSalaryComparison(int year, int month) {
        Map<String, BigDecimal> stats = getDepartmentAvgSalaryStats(year, month);
        return ChartUtil.createDepartmentAvgSalaryBarChart(stats);
    }

    @Override
    public Node getSalaryComponentsAnalysis(int year, int month) {
        Map<String, Map<String, BigDecimal>> stats = getSalaryComponentStats(year, month);
        return ChartUtil.createSalaryComponentsStackedBarChart(stats);
    }

    @Override
    public Node getYearlySalaryTrend() {
        Map<Integer, BigDecimal> stats = getYearlySalaryStats();
        return ChartUtil.createYearlySalaryAreaChart(stats);
    }

    @Override
    public Map<String, BigDecimal> getDepartmentSalaryStats(int year, int month) {
        return salaryMapper.getDepartmentSalaryStats(year, month);
    }

    @Override
    public Map<String, BigDecimal> getMonthlySalaryStats(int year) {
        return salaryMapper.getMonthlySalaryStats(year);
    }

    @Override
    public Map<String, Integer> getSalaryRangeStats(int year, int month) {
        List<BigDecimal> salaries = salaryMapper.getAllSalaries(year, month);
        return calculateSalaryRanges(salaries);
    }

    @Override
    public Map<String, BigDecimal> getDepartmentAvgSalaryStats(int year, int month) {
        return salaryMapper.getDepartmentAvgSalaryStats(year, month);
    }

    @Override
    public Map<String, Map<String, BigDecimal>> getSalaryComponentStats(int year, int month) {
        return salaryMapper.getSalaryComponentStats(year, month);
    }

    @Override
    public Map<Integer, BigDecimal> getYearlySalaryStats() {
        return salaryMapper.getYearlySalaryStats();
    }

    /**
     * 计算工资范围分布
     */
    private Map<String, Integer> calculateSalaryRanges(List<BigDecimal> salaries) {
        if (salaries == null || salaries.isEmpty()) {
            return new LinkedHashMap<>();
        }

        // 定义工资范围
        BigDecimal[] ranges = {
                BigDecimal.valueOf(3000),
                BigDecimal.valueOf(5000),
                BigDecimal.valueOf(8000),
                BigDecimal.valueOf(12000),
                BigDecimal.valueOf(15000),
                BigDecimal.valueOf(20000),
                BigDecimal.valueOf(30000)
        };

        // 初始化结果Map，保持范围顺序
        Map<String, Integer> result = new LinkedHashMap<>();
        result.put("3000以下", 0);
        result.put("3000-5000", 0);
        result.put("5000-8000", 0);
        result.put("8000-12000", 0);
        result.put("12000-15000", 0);
        result.put("15000-20000", 0);
        result.put("20000-30000", 0);
        result.put("30000以上", 0);

        // 统计每个范围的人数
        for (BigDecimal salary : salaries) {
            String range = getRangeDescription(salary, ranges);
            result.put(range, result.get(range) + 1);
        }

        return result;
    }

    /**
     * 获取工资范围描述
     */
    private String getRangeDescription(BigDecimal salary, BigDecimal[] ranges) {
        for (int i = 0; i < ranges.length; i++) {
            if (salary.compareTo(ranges[i]) < 0) {
                if (i == 0) {
                    return ranges[0] + "以下";
                } else {
                    return ranges[i - 1] + "-" + ranges[i];
                }
            }
        }
        return ranges[ranges.length - 1] + "以上";
    }
}