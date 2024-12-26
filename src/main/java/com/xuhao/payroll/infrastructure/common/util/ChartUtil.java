package com.xuhao.payroll.infrastructure.common.util;

import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 图表工具类
 */
public class ChartUtil {
    private ChartUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 创建部门工资分布饼图
     *
     * @param departmentSalaries 部门工资数据（部门名称 -> 总工资）
     * @return 饼图节点
     */
    public static Node createDepartmentSalaryPieChart(Map<String, BigDecimal> departmentSalaries) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        departmentSalaries.forEach((dept, salary) -> pieChartData.add(new PieChart.Data(dept, salary.doubleValue())));

        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("部门工资分布");

        // 添加数据标签
        pieChartData.forEach(data -> {
            String percentage = String.format("%.1f%%", (data.getPieValue() /
                    departmentSalaries.values().stream()
                            .mapToDouble(BigDecimal::doubleValue)
                            .sum())
                    * 100);
            Tooltip tooltip = new Tooltip(data.getName() + "\n" +
                    String.format("%,.2f元 (%s)", data.getPieValue(), percentage));
            Tooltip.install(data.getNode(), tooltip);
        });

        return chart;
    }

    /**
     * 创建月度工资趋势折线图
     *
     * @param monthlySalaries 月度工资数据（月份 -> 总工资）
     * @return 折线图节点
     */
    public static Node createMonthlySalaryLineChart(Map<String, BigDecimal> monthlySalaries) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("月份");
        yAxis.setLabel("工资总额（元）");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("月度工资趋势");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("工资总额");

        monthlySalaries.forEach((month, salary) -> {
            series.getData().add(new XYChart.Data<>(
                    Integer.parseInt(month),
                    salary.doubleValue()));
        });

        lineChart.getData().add(series);

        // 添加数据标签
        series.getData().forEach(data -> {
            Tooltip tooltip = new Tooltip(String.format("%d月\n%,.2f元",
                    data.getXValue().intValue(),
                    data.getYValue().doubleValue()));
            Tooltip.install(data.getNode(), tooltip);
        });

        return lineChart;
    }

    /**
     * 创建员工工资分布柱状图
     *
     * @param salaryRanges 工资范围分布（范围描述 -> 人数）
     * @return 柱状图节点
     */
    public static Node createSalaryDistributionBarChart(Map<String, Integer> salaryRanges) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("工资范围");
        yAxis.setLabel("人数");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("员工工资分布");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("人数分布");

        salaryRanges.forEach((range, count) -> series.getData().add(new XYChart.Data<>(range, count)));

        barChart.getData().add(series);

        // 添加数据标签
        series.getData().forEach(data -> {
            Tooltip tooltip = new Tooltip(String.format("%s\n%d人",
                    data.getXValue(),
                    data.getYValue().intValue()));
            Tooltip.install(data.getNode(), tooltip);
        });

        return barChart;
    }

    /**
     * 创建部门平均工资对比柱状图
     *
     * @param departmentAvgSalaries 部门平均工资数据（部门名称 -> 平均工资）
     * @return 柱状图节点
     */
    public static Node createDepartmentAvgSalaryBarChart(Map<String, BigDecimal> departmentAvgSalaries) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("部门");
        yAxis.setLabel("平均工资（元）");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("部门平均工资对比");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("平均工资");

        departmentAvgSalaries
                .forEach((dept, salary) -> series.getData().add(new XYChart.Data<>(dept, salary.doubleValue())));

        barChart.getData().add(series);

        // 添加数据标签
        series.getData().forEach(data -> {
            Tooltip tooltip = new Tooltip(String.format("%s\n%,.2f元",
                    data.getXValue(),
                    data.getYValue().doubleValue()));
            Tooltip.install(data.getNode(), tooltip);
        });

        return barChart;
    }

    /**
     * 创建工资组成堆叠柱状图
     *
     * @param salaryComponents 工资组成数据（员工ID -> Map<组成部分, 金额>）
     * @return 堆叠柱状图节点
     */
    public static Node createSalaryComponentsStackedBarChart(
            Map<String, Map<String, BigDecimal>> salaryComponents) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("员工");
        yAxis.setLabel("金额（元）");

        StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);
        stackedBarChart.setTitle("工资组成分析");

        // 获取所有工资组成部分
        List<String> components = salaryComponents.values().stream()
                .flatMap(map -> map.keySet().stream())
                .distinct()
                .collect(Collectors.toList());

        // 为每个组成部分创建一个系列
        components.forEach(component -> {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(component);

            salaryComponents.forEach((empId, componentMap) -> {
                BigDecimal value = componentMap.getOrDefault(component, BigDecimal.ZERO);
                series.getData().add(new XYChart.Data<>(empId, value.doubleValue()));
            });

            stackedBarChart.getData().add(series);
        });

        // 添加数据标签
        stackedBarChart.getData().forEach(series -> {
            series.getData().forEach(data -> {
                Tooltip tooltip = new Tooltip(String.format("%s\n%s: %,.2f元",
                        data.getXValue(),
                        series.getName(),
                        data.getYValue().doubleValue()));
                Tooltip.install(data.getNode(), tooltip);
            });
        });

        return stackedBarChart;
    }

    /**
     * 创建年度工资趋势区域图
     *
     * @param yearlySalaries 年度工资数据（年份 -> 总工资）
     * @return 区域图节点
     */
    public static Node createYearlySalaryAreaChart(Map<Integer, BigDecimal> yearlySalaries) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("年份");
        yAxis.setLabel("工资总额（元）");

        AreaChart<Number, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("年度工资趋势");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("工资总额");

        yearlySalaries.forEach((year, salary) -> series.getData().add(new XYChart.Data<>(year, salary.doubleValue())));

        areaChart.getData().add(series);

        // 添加数据标签
        series.getData().forEach(data -> {
            Tooltip tooltip = new Tooltip(String.format("%d年\n%,.2f元",
                    data.getXValue().intValue(),
                    data.getYValue().doubleValue()));
            Tooltip.install(data.getNode(), tooltip);
        });

        return areaChart;
    }
}