package com.xuhao.payroll.infrastructure.common.util;

import javafx.scene.control.Alert;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * IP地址工具类
 */
public class IpUtil {
    private IpUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取本机IP地址
     * 
     * @return IP地址
     */
    public static String getLocalIp() {
        try {
            // 优先获取非回环地址
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address.isSiteLocalAddress()) {
                        return address.getHostAddress();
                    }
                }
            }

            // 如果没有找到非回环地址，则返回本地回环地址
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            showError("获取IP地址失败: " + e.getMessage());
            return "unknown";
        }
    }

    /**
     * 显示错误提示
     * 
     * @param message 错误信息
     */
    private static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}