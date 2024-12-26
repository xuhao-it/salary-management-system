package com.xuhao.payroll.presentation.view;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class MainView extends BorderPane {

    private final VBox sideBar;
    private final StackPane contentArea;

    public MainView() {
        // 初始化侧边栏
        sideBar = createSideBar();
        setLeft(sideBar);

        // 初始化内容区域
        contentArea = new StackPane();
        contentArea.getStyleClass().add("content-area");
        setCenter(contentArea);

        // 设置样式
        getStyleClass().add("main-view");
        getStylesheets().add("/css/main.css");
    }

    private VBox createSideBar() {
        VBox sideBar = new VBox(10);
        sideBar.setPadding(new Insets(20));
        sideBar.setAlignment(Pos.TOP_CENTER);
        sideBar.getStyleClass().add("side-bar");

        // 添加标题
        Label title = new Label("工资管理系统");
        title.setFont(Font.font(20));
        title.getStyleClass().add("side-bar-title");

        // 创建导航按钮
        MFXButton dashboardBtn = createNavButton("仪表盘", e -> showDashboard());
        MFXButton salaryBtn = createNavButton("工资管理", e -> showSalaryManagement());
        MFXButton employeeBtn = createNavButton("员工管理", e -> showEmployeeManagement());
        MFXButton departmentBtn = createNavButton("部门管理", e -> showDepartmentManagement());
        MFXButton statisticsBtn = createNavButton("统计报表", e -> showStatistics());
        MFXButton settingsBtn = createNavButton("系统设置", e -> showSettings());

        // 将组件添加到侧边栏
        sideBar.getChildren().addAll(
                title,
                new VBox(20), // 间隔
                dashboardBtn,
                salaryBtn,
                employeeBtn,
                departmentBtn,
                statisticsBtn,
                settingsBtn);

        return sideBar;
    }

    private MFXButton createNavButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        MFXButton button = new MFXButton(text);
        button.getStyleClass().add("nav-button");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(action);
        return button;
    }

    // 导航方法
    private void showDashboard() {
        // TODO: 显示仪表盘
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("仪表盘"));
    }

    private void showSalaryManagement() {
        // TODO: 显示工资管理界面
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("工资管理"));
    }

    private void showEmployeeManagement() {
        // TODO: 显示员工管理界面
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("员工管理"));
    }

    private void showDepartmentManagement() {
        // TODO: 显示部门管理界面
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("部门管理"));
    }

    private void showStatistics() {
        // TODO: 显示统计报表界面
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("统计报表"));
    }

    private void showSettings() {
        // TODO: 显示系统设置界面
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("系统设置"));
    }
}