package com.xuhao.payroll.infrastructure.common.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据转换工具类
 */
public class ConversionUtil {
    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"));

    private static final List<DateTimeFormatter> DATETIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));

    private ConversionUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 转换为Integer
     */
    public static Integer toInteger(Object value) {
        if (value == null)
            return null;
        if (value instanceof Integer)
            return (Integer) value;
        if (value instanceof Number)
            return ((Number) value).intValue();
        if (value instanceof String) {
            try {
                return Integer.parseInt(((String) value).trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 转换为Long
     */
    public static Long toLong(Object value) {
        if (value == null)
            return null;
        if (value instanceof Long)
            return (Long) value;
        if (value instanceof Number)
            return ((Number) value).longValue();
        if (value instanceof String) {
            try {
                return Long.parseLong(((String) value).trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 转换为BigDecimal
     */
    public static BigDecimal toBigDecimal(Object value) {
        if (value == null)
            return null;
        if (value instanceof BigDecimal)
            return (BigDecimal) value;
        if (value instanceof Number)
            return BigDecimal.valueOf(((Number) value).doubleValue());
        if (value instanceof String) {
            try {
                return new BigDecimal(((String) value).trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 转换为LocalDate
     */
    public static LocalDate toLocalDate(Object value) {
        if (value == null)
            return null;
        if (value instanceof LocalDate)
            return (LocalDate) value;
        if (value instanceof LocalDateTime)
            return ((LocalDateTime) value).toLocalDate();
        if (value instanceof String) {
            String str = ((String) value).trim();
            for (DateTimeFormatter formatter : DATE_FORMATTERS) {
                try {
                    return LocalDate.parse(str, formatter);
                } catch (DateTimeParseException e) {
                    // 继续尝试下一个格式
                }
            }
        }
        return null;
    }

    /**
     * 转换为LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Object value) {
        if (value == null)
            return null;
        if (value instanceof LocalDateTime)
            return (LocalDateTime) value;
        if (value instanceof LocalDate)
            return ((LocalDate) value).atStartOfDay();
        if (value instanceof String) {
            String str = ((String) value).trim();
            for (DateTimeFormatter formatter : DATETIME_FORMATTERS) {
                try {
                    return LocalDateTime.parse(str, formatter);
                } catch (DateTimeParseException e) {
                    // 继续尝试下一个格式
                }
            }
        }
        return null;
    }

    /**
     * 转换为Boolean
     */
    public static Boolean toBoolean(Object value) {
        if (value == null)
            return null;
        if (value instanceof Boolean)
            return (Boolean) value;
        if (value instanceof String) {
            String str = ((String) value).trim().toLowerCase();
            return "true".equals(str) || "1".equals(str) || "yes".equals(str);
        }
        if (value instanceof Number) {
            return ((Number) value).intValue() == 1;
        }
        return null;
    }

    /**
     * 转换为String
     */
    public static String toString(Object value) {
        if (value == null)
            return null;
        if (value instanceof String)
            return ((String) value).trim();
        return value.toString();
    }

    /**
     * 转换为List<String>
     */
    public static List<String> toStringList(String value, String delimiter) {
        if (value == null || value.trim().isEmpty())
            return null;
        return Arrays.stream(value.split(delimiter))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * 转换为List<Integer>
     */
    public static List<Integer> toIntegerList(String value, String delimiter) {
        if (value == null || value.trim().isEmpty())
            return null;
        return Arrays.stream(value.split(delimiter))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(ConversionUtil::toInteger)
                .filter(i -> i != null)
                .collect(Collectors.toList());
    }

    /**
     * 转换为List<Long>
     */
    public static List<Long> toLongList(String value, String delimiter) {
        if (value == null || value.trim().isEmpty())
            return null;
        return Arrays.stream(value.split(delimiter))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(ConversionUtil::toLong)
                .filter(l -> l != null)
                .collect(Collectors.toList());
    }
}