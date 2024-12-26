package com.xuhao.payroll.infrastructure.common.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 数据验证工具类
 */
public class ValidationUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^1[3-9]\\d{9}$");

    private static final Pattern ID_CARD_PATTERN = Pattern.compile(
            "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");

    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 验证结果类
     */
    public static class ValidationResult {
        private final List<String> errors = new ArrayList<>();

        public void addError(String error) {
            errors.add(error);
        }

        public boolean hasErrors() {
            return !errors.isEmpty();
        }

        public List<String> getErrors() {
            return new ArrayList<>(errors);
        }

        public String getErrorMessage() {
            return String.join("\n", errors);
        }
    }

    /**
     * 验证邮箱格式
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 验证手机号格式
     */
    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * 验证身份证号格式
     */
    public static boolean isValidIdCard(String idCard) {
        return idCard != null && ID_CARD_PATTERN.matcher(idCard).matches();
    }

    /**
     * 验证金额
     */
    public static boolean isValidAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    /**
     * 验证日期范围
     */
    public static boolean isValidDateRange(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null && !startDate.isAfter(endDate);
    }

    /**
     * 验证时间范围
     */
    public static boolean isValidDateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime != null && endTime != null && !startTime.isAfter(endTime);
    }

    /**
     * 验证字符串长度
     */
    public static boolean isValidLength(String str, int minLength, int maxLength) {
        return str != null && str.length() >= minLength && str.length() <= maxLength;
    }

    /**
     * 验证数值范围
     */
    public static boolean isValidRange(BigDecimal value, BigDecimal min, BigDecimal max) {
        return value != null &&
                (min == null || value.compareTo(min) >= 0) &&
                (max == null || value.compareTo(max) <= 0);
    }

    /**
     * 验证整数范围
     */
    public static boolean isValidRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * 验证非空字符串
     */
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * 验证是否为正整数
     */
    public static boolean isPositiveInteger(Integer value) {
        return value != null && value > 0;
    }

    /**
     * 验证是否为非负整数
     */
    public static boolean isNonNegativeInteger(Integer value) {
        return value != null && value >= 0;
    }

    /**
     * 验证是否为正数
     */
    public static boolean isPositive(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * 验证是否为非负数
     */
    public static boolean isNonNegative(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) >= 0;
    }
}