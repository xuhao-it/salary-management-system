package com.xuhao.payroll.infrastructure.common.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel导入工具类
 */
public class ExcelImportUtil {
    private ExcelImportUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 从Excel文件导入数据
     *
     * @param filePath      Excel文件路径
     * @param clazz         目标类型
     * @param headerMapping 表头映射（Excel表头 -> 字段名）
     * @param <T>           目标类型
     * @return 导入的数据列表
     * @throws IOException IO异常
     */
    public static <T> List<T> importFromExcel(String filePath, Class<T> clazz,
            Map<String, String> headerMapping) throws IOException {
        List<T> resultList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook;
            if (filePath.toLowerCase().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.toLowerCase().endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("不支持的文件格式");
            }

            Sheet sheet = workbook.getSheetAt(0);
            // 获取表头行
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IllegalArgumentException("Excel文件为空");
            }

            // 解析表头
            Map<Integer, String> columnMapping = new HashMap<>();
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null) {
                    String headerName = cell.getStringCellValue().trim();
                    String fieldName = headerMapping.get(headerName);
                    if (fieldName != null) {
                        columnMapping.put(i, fieldName);
                    }
                }
            }

            // 解析数据行
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;

                T instance = clazz.getDeclaredConstructor().newInstance();
                boolean hasData = false;

                for (Map.Entry<Integer, String> entry : columnMapping.entrySet()) {
                    Cell cell = row.getCell(entry.getKey());
                    if (cell != null) {
                        String fieldName = entry.getValue();
                        Field field = clazz.getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Object value = getCellValue(cell, field.getType());
                        if (value != null) {
                            field.set(instance, value);
                            hasData = true;
                        }
                    }
                }

                if (hasData) {
                    resultList.add(instance);
                }
            }

            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException("导入Excel失败", e);
        }

        return resultList;
    }

    private static Object getCellValue(Cell cell, Class<?> fieldType) {
        if (cell == null)
            return null;

        switch (cell.getCellType()) {
            case STRING:
                String stringValue = cell.getStringCellValue();
                if (fieldType == LocalDate.class) {
                    return LocalDate.parse(stringValue);
                } else if (fieldType == LocalDateTime.class) {
                    return LocalDateTime.parse(stringValue);
                }
                return stringValue;

            case NUMERIC:
                if (cell.getCellStyle().getDataFormat() > 0) {
                    if (fieldType == LocalDate.class) {
                        return cell.getLocalDateTimeCellValue().toLocalDate();
                    } else if (fieldType == LocalDateTime.class) {
                        return cell.getLocalDateTimeCellValue();
                    }
                }
                double numericValue = cell.getNumericCellValue();
                if (fieldType == Integer.class || fieldType == int.class) {
                    return (int) numericValue;
                } else if (fieldType == Long.class || fieldType == long.class) {
                    return (long) numericValue;
                } else if (fieldType == Double.class || fieldType == double.class) {
                    return numericValue;
                } else if (fieldType == BigDecimal.class) {
                    return BigDecimal.valueOf(numericValue);
                }
                return numericValue;

            case BOOLEAN:
                return cell.getBooleanCellValue();

            case FORMULA:
                try {
                    switch (cell.getCachedFormulaResultType()) {
                        case NUMERIC:
                            return getCellValue(cell, fieldType);
                        case STRING:
                            return cell.getStringCellValue();
                        case BOOLEAN:
                            return cell.getBooleanCellValue();
                        default:
                            return null;
                    }
                } catch (Exception e) {
                    return null;
                }

            default:
                return null;
        }
    }
}