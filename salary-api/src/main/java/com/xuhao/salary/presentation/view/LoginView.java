package com.xuhao.payroll.presentation.view;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class LoginView extends VBox {

    private final MFXTextField usernameField;
    private final MFXPasswordField passwordField;
    private final MFXButton loginButton;
    private final Label errorLabel;
    private final MFXProgressSpinner loadingSpinner;

    public LoginView() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(50));
        setSpacing(20);
        getStyleClass().add("login-view");
        getStylesheets().add("/css/login.css");

        // 创建标题
        Label titleLabel = new Label("工资管理系统");
        titleLabel.setFont(Font.font(24));
        titleLabel.getStyleClass().add("login-title");

        // 创建用户名输入框
        usernameField = new MFXTextField();
        usernameField.setFloatingText("用户名");
        usernameField.setPrefWidth(300);
        usernameField.getStyleClass().add("login-field");

        // 创建密码输入框
        passwordField = new MFXPasswordField();
        passwordField.setFloatingText("密码");
        passwordField.setPrefWidth(300);
        passwordField.getStyleClass().add("login-field");

        // 创建错误提示标签
        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
        errorLabel.getStyleClass().add("error-label");

        // 创建加载动画
        loadingSpinner = new MFXProgressSpinner();
        loadingSpinner.setRadius(15);
        loadingSpinner.setVisible(false);
        loadingSpinner.getStyleClass().add("loading-spinner");

        // 创建登录按钮
        loginButton = new MFXButton("登录");
        loginButton.setPrefWidth(300);
        loginButton.getStyleClass().add("login-button");
        loginButton.setOnAction(e -> handleLogin());

        // 添加组件到布局
        getChildren().addAll(
                titleLabel,
                usernameField,
                passwordField,
                errorLabel,
                loadingSpinner,
                loginButton);

        // 添加输入框监听器
        usernameField.textProperty().addListener((obs, oldText, newText) -> {
            errorLabel.setVisible(false);
        });
        passwordField.textProperty().addListener((obs, oldText, newText) -> {
            errorLabel.setVisible(false);
        });
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // 输入验证
        if (username.isEmpty() || password.isEmpty()) {
            showError("用户名和密码不能为空");
            return;
        }

        // 禁用登录按钮和输入框
        setControlsDisabled(true);
        // 显示加载动画
        loadingSpinner.setVisible(true);

        // 模拟登录过程
        new Thread(() -> {
            try {
                Thread.sleep(1500); // 模拟网络延迟

                Platform.runLater(() -> {
                    if ("admin".equals(username) && "admin".equals(password)) {
                        // 登录成功
                        loginSuccess();
                    } else {
                        // 登录失败
                        showError("用户名或密码错误");
                        setControlsDisabled(false);
                    }
                    loadingSpinner.setVisible(false);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    showError("登录过程中发生错误");
                    setControlsDisabled(false);
                    loadingSpinner.setVisible(false);
                });
            }
        }).start();
    }

    private void loginSuccess() {
        // 创建淡出动画
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), this);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> {
            // 切换到主界面
            getScene().setRoot(new MainView());
        });
        fadeOut.play();
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);

        // 添加抖动动画
        shakeNode(errorLabel);
    }

    private void setControlsDisabled(boolean disabled) {
        usernameField.setDisable(disabled);
        passwordField.setDisable(disabled);
        loginButton.setDisable(disabled);
    }

    private void shakeNode(javafx.scene.Node node) {
        javafx.animation.TranslateTransition shake = new javafx.animation.TranslateTransition(Duration.millis(100),
                node);
        shake.setFromX(0);
        shake.setByX(10);
        shake.setCycleCount(4);
        shake.setAutoReverse(true);
        shake.play();
    }
}