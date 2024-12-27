package com.xuhao.payroll.service;

import com.xuhao.payroll.model.Salary;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.util.List;

/**
 * 工资条服务接口
 */
public interface PayslipService {
    /**
     * 导出单个员工工资条PDF
     * 
     * @param salary     工资信息
     * @param outputPath 输出路径
     */
    void exportPayslipPdf(Salary salary, String outputPath) throws IOException, DocumentException;

    /**
     * 批量导出工资条PDF
     * 
     * @param salaryList 工资信息列表
     * @param outputDir  输出目录
     * @return 导出的文件路径列表
     */
    List<String> batchExportPayslipPdf(List<Salary> salaryList, String outputDir) throws IOException, DocumentException;

    /**
     * 导出工资表Excel
     * 
     * @param salaryList 工资信息列表
     * @param outputPath 输出路径
     */
    void exportSalaryExcel(List<Salary> salaryList, String outputPath) throws IOException, DocumentException;
}