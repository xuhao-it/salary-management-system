package com.xuhao.payroll.infrastructure.common.util;

import org.mindrot.jbcrypt.BCrypt;
import java.util.regex.Pattern;

/**
 * 密码工具类
 */
public class PasswordUtil {
    private static final int WORKLOAD = 12; // BCrypt工作因子
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    private PasswordUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 使用BCrypt加密密码
     * 
     * @param password 原始密码
     * @return 加密后的密码
     */
    public static String encrypt(String password) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(password, salt);
    }

    /**
     * 验证密码
     * 
     * @param password       原始密码
     * @param hashedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verify(String password, String hashedPassword) {
        try {
            return BCrypt.checkpw(password, hashedPassword);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 验证密码强度
     * 
     * @param password 待验证的密码
     * @return 是否符合强度要求
     */
    public static boolean isStrongPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * 生成随机强密码
     * 
     * @return 随机密码
     */
    public static String generateStrongPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%^&+=";
        StringBuilder password = new StringBuilder();
        java.security.SecureRandom random = new java.security.SecureRandom();

        // 确保包含所有必需的字符类型
        password.append((char) ('A' + random.nextInt(26))); // 大写字母
        password.append((char) ('a' + random.nextInt(26))); // 小写字母
        password.append((char) ('0' + random.nextInt(10))); // 数字
        password.append("@#$%^&+=".charAt(random.nextInt(8))); // 特殊字符

        // 添加随机字符直到达到12位
        while (password.length() < 12) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        // 打乱密码字符顺序
        char[] passwordArray = password.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }

        return new String(passwordArray);
    }
}