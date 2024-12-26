package com.xuhao.payroll.service.impl;

import com.xuhao.payroll.infrastructure.common.util.ExcelUtil;
import com.xuhao.payroll.infrastructure.common.util.PdfUtil;
import com.xuhao.payroll.model.Salary;
import com.xuhao.payroll.service.PayslipService;
import org.springframework.stereotype.Service;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 工资条服务实现类
 */
@Service
public class PayslipServiceImpl implements PayslipService {

    private static final String[] EXCEL_HEADERS = {
            "员工编号", "姓名", "部门", "年份", "月份", "基本工资", "岗位工资", "加班工资", "奖金",
            "社会保", "住房公积金", "个人所得税", "其他扣除", "实发工资", "状态", "备注"
    };

    private static final String[] EXCEL_FIELDS = {
            "empId", "empName", "deptName", "year", "month", "basicSalary", "positionSalary",
            "overtimePay", "bonus", "insurance", "housingFund", "tax", "deduction", "netSalary",
            "status", "remark"
    };

    @Override
    public void exportPayslipPdf(Salary salary, String outputPath) throws IOException, DocumentException {
        try {
            PdfUtil.generatePayslip(salary, outputPath);
        } catch (IOException e) {
            throw new RuntimeException("导出工资条PDF失败", e);
        }
    }

    @Override
    public List<String> batchExportPayslipPdf(List<Salary> salaryList, String outputDir) {
        List<String> exportedFiles = new ArrayList<>();
        File dir = new File(outputDir);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new RuntimeException("创建输出目录失败");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        for (Salary salary : salaryList) {
            String fileName = String.format("%s_%d_%s.pdf",
                    salary.getEmpId(),
                    salary.getYear(),
                    String.format("%02d", salary.getMonth()));
            String filePath = new File(outputDir, fileName).getAbsolutePath();

            try {
                exportPayslipPdf(salary, filePath);
                exportedFiles.add(filePath);
            } catch (Exception e) {
                // 记录错误但继续处理其他工资条
                e.printStackTrace();
            }
        }

        return exportedFiles;
    }

    @Override
    public void exportSalaryExcel(List<Salary> salaryList, String outputPath) {
        try {
            ExcelUtil.exportToExcel(salaryList, EXCEL_HEADERS, EXCEL_FIELDS, outputPath);
        } catch (IOException e) {
            throw new RuntimeException("导出工资表Excel失败", e);
        }
    }
}