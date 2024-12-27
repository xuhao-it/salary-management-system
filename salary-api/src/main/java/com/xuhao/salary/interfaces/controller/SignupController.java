package com.xuhao.payroll.interfaces.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML
    private VBox rootPane;
    @FXML
    private MFXTextField usernameField;
    @FXML
    private MFXTextField emailField;
    @FXML
    private MFXPasswordField passwordField;
    @FXML
    private MFXPasswordField confirmPasswordField;
    @FXML
    private MFXButton signupButton;

    @FXML
    public void initialize() {
        // 设置默认焦点到用户名输入框
        usernameField.requestFocus();

        // 添加回车键监听
        usernameField.setOnAction(event -> emailField.requestFocus());
        emailField.setOnAction(event -> passwordField.requestFocus());
        passwordField.setOnAction(event -> confirmPasswordField.requestFocus());
        confirmPasswordField.setOnAction(event -> handleSignup());
    }

    @FXML
    private void handleBack() {
        loadLoginView();
    }

    @FXML
    private void handleLogin() {
        loadLoginView();
    }

    @FXML
    private void handleSignup() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // 验证输入
        if (username.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "注册提示", "请输入用户名");
            usernameField.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "注册提示", "请输入邮箱地址");
            emailField.requestFocus();
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showAlert(Alert.AlertType.WARNING, "注册提示", "请输入有效的邮箱地址");
            emailField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "注册提示", "请输入密码");
            passwordField.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "注册提示", "请确认密码");
            confirmPasswordField.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.WARNING, "注册提示", "两次输入的密码不一致");
            confirmPasswordField.clear();
            confirmPasswordField.requestFocus();
            return;
        }

        // TODO: 实现注册逻辑
        showAlert(Alert.AlertType.INFORMATION, "注册成功", "账号注册成功！");
        loadLoginView();
    }

    private void loadLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "错误", "加载登录界面失败");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}