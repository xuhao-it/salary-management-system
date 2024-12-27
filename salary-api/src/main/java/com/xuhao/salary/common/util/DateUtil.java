package com.xuhao.payroll.infrastructure.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.xuhao.payroll.infrastructure.common.constant.SystemConst;

/**
 * 日期工具类
 */
public class DateUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(SystemConst.DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(SystemConst.TIME_FORMAT);
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter
            .ofPattern(SystemConst.DATETIME_FORMAT);

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 格式化日期
     * 
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : "";
    }

    /**
     * 格式化日期时间
     * 
     * @param dateTime 日期时间
     * @return 格式化后的字符串
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATETIME_FORMATTER) : "";
    }

    /**
     * 解析日期字符串
     * 
     * @param dateStr 日期字符串
     * @return 日期对象
     */
    public static LocalDate parseDate(String dateStr) {
        return dateStr != null && !dateStr.isEmpty() ? LocalDate.parse(dateStr, DATE_FORMATTER) : null;
    }

    /**
     * 解析日期时间字符串
     * 
     * @param dateTimeStr 日期时间字符串
     * @return 日期时间对象
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return dateTimeStr != null && !dateTimeStr.isEmpty() ? LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER)
                : null;
    }

    /**
     * 获取当前日期
     * 
     * @return 当前日期字符串
     */
    public static String getCurrentDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * 获取当前时间
     * 
     * @return 当前时间字符串
     */
    public static String getCurrentTime() {
        return LocalDateTime.now().format(TIME_FORMATTER);
    }

    /**
     * 获取当前日期时间
     * 
     * @return 当前日期时间字符串
     */
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }
}