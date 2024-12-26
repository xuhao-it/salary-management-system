package com.xuhao.payroll.util;

import java.security.MessageDigest;
import java.util.Base64;

public class SecurityUtils {
    
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("密码加密失败", e);
        }
    }

    public static boolean validatePassword(String inputPassword, String storedHash) {
        String inputHash = hashPassword(inputPassword);
        return inputHash.equals(storedHash);
    }

    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        // 移除SQL注入风险字符
        return input.replaceAll("[';\"\\\\]", "");
    }
}
