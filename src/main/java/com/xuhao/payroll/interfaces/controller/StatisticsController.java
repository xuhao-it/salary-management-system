package com.xuhao.payroll.controller;

import com.xuhao.payroll.service.StatisticsService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * 统计报表控制器
 */
@Controller
public class StatisticsController implements Initializable {

    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private ComboBox<Integer> monthComboBox;

    @FXML
    private GridPane chartsGrid;

    @FXML
    private TabPane tabPane;

    @Autowired
    private StatisticsService statisticsService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeControls();
        setupEventHandlers();
        refreshCharts();
    }

    private void initializeControls() {
        // 初始化年份下拉框
        int currentYear = LocalDate.now().getYear();
        for (int year = currentYear - 5; year <= currentYear; year++) {
            yearComboBox.getItems().add(year);
        }
        yearComboBox.setValue(currentYear);

        // 初始化月份下拉框
        for (int month = 1; month <= 12; month++) {
            monthComboBox.getItems().add(month);
        }
        monthComboBox.setValue(LocalDate.now().getMonthValue());
    }

    private void setupEventHandlers() {
        // 年份或月份变化时刷新图表
        yearComboBox.setOnAction(e -> refreshCharts());
        monthComboBox.setOnAction(e -> refreshCharts());
    }

    private void refreshCharts() {
        int year = yearComboBox.getValue();
        int month = monthComboBox.getValue();

        // 清空现有图表
        chartsGrid.getChildren().clear();

        // 添加部门工资分布饼图
        Node departmentSalaryChart = statisticsService.getDepartmentSalaryDistribution(year, month);
        addChartToGrid(departmentSalaryChart, 0, 0);

        // 添加月度工资趋势折线图
        Node monthlySalaryChart = statisticsService.getMonthlySalaryTrend(year);
        addChartToGrid(monthlySalaryChart, 0, 1);

        // 添加员工工资分布柱状图
        Node salaryDistributionChart = statisticsService.getEmployeeSalaryDistribution(year, month);
        addChartToGrid(salaryDistributionChart, 1, 0);

        // 添加部门平均工资对比柱状图
        Node departmentAvgSalaryChart = statisticsService.getDepartmentAvgSalaryComparison(year, month);
        addChartToGrid(departmentAvgSalaryChart, 1, 1);

        // 添加工资组成堆叠柱状图
        Node salaryComponentsChart = statisticsService.getSalaryComponentsAnalysis(year, month);
        addChartToGrid(salaryComponentsChart, 2, 0);

        // 添加年度工资趋势区域图
        Node yearlySalaryChart = statisticsService.getYearlySalaryTrend();
        addChartToGrid(yearlySalaryChart, 2, 1);
    }

    private void addChartToGrid(Node chart, int row, int col) {
        // 创建图表容器
        StackPane chartContainer = new StackPane(chart);
        chartContainer.getStyleClass().add("chart-container");

        // 设置图表大小自适应
        chart.setStyle("-fx-padding: 10;");
        chartContainer.prefWidthProperty().bind(chartsGrid.widthProperty().divide(2).subtract(10));
        chartContainer.prefHeightProperty().bind(chartsGrid.heightProperty().divide(3).subtract(10));

        // 添加到网格
        chartsGrid.add(chartContainer, col, row);
    }

    @FXML
    private void exportCharts() {
        // TODO: 实现图表导出功能
    }

    @FXML
    private void printCharts() {
        // TODO: 实现图表打印功能
    }

    @FXML
    private void refreshData() {
        refreshCharts();
    }
}