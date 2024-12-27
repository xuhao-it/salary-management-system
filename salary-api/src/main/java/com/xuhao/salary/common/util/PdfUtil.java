package com.xuhao.payroll.infrastructure.common.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.xuhao.payroll.model.Employee;  // 添加 Employee 类的导入
import com.xuhao.payroll.model.Salary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

/**
 * PDF导出工具类
 */
public class PdfUtil {
    private static final String FONT_PATH = "/fonts/simsun.ttf"; // 宋体字体文件路径

    private PdfUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 生成工资条PDF
     *
     * @param salary     工资信息
     * @param outputPath 输出文件路径
     * @throws IOException IO异常
     * @throws DocumentException 文档异常
     */
    public static void generatePayslip(Salary salary, Employee employee) throws Exception {
        Document document = new Document(PageSize.A4);
        String fileName = String.format("工资单_%d年%d月_%d.pdf", 
            salary.getYear(), salary.getMonth(), salary.getEmployeeId());
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        
        BaseFont baseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);
        
        // 添加标题
        Paragraph title = new Paragraph("工资条", new Font(baseFont, 20, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // 添加基本信息
        document.add(new Paragraph(String.format("发薪月份：%d年%d月", salary.getYear(), salary.getMonth()), font));
        document.add(new Paragraph("员工信息：" + salary.getEmpId(), font)); // TODO: 获取员工姓名等信息

        // 创建工资明细表格
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        // 添加表头
        addTableHeader(table);

        // 添加工资项目
        addTableRow(table, "基本工资", formatAmount(salary.getBasicSalary()));
        addTableRow(table, "岗位工资", formatAmount(salary.getPositionSalary()));
        addTableRow(table, "加班工资", formatAmount(salary.getOvertimePay()));
        addTableRow(table, "奖金", formatAmount(salary.getBonus()));

        // 计算应发工资
        BigDecimal totalSalary = salary.getBasicSalary()
                .add(salary.getPositionSalary())
                .add(salary.getOvertimePay())
                .add(salary.getBonus());
        addTableRow(table, "应发工资", formatAmount(totalSalary));

        // 添加扣除项目
        addTableRow(table, "社会保险", formatAmount(salary.getSocialInsurance()));
        addTableRow(table, "住房公积金", formatAmount(salary.getHousingFund()));
        addTableRow(table, "个人所得税", formatAmount(salary.getPersonalIncomeTax()));
        addTableRow(table, "其他扣除", formatAmount(salary.getDeduction()));
        addTableRow(table, "实发工资", formatAmount(salary.getNetSalary()));

        document.add(table);

        // 添加备注
        if (salary.getRemark() != null && !salary.getRemark().isEmpty()) {
            document.add(new Paragraph("\n备注：" + salary.getRemark(), font));
        }

        // 添加生成时间
        Font smallFont = new Font(baseFont, 10);
        Paragraph timeStamp = new Paragraph("\n生成时间：" +
                salary.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), smallFont);
        timeStamp.setAlignment(Element.ALIGN_RIGHT);
        document.add(timeStamp);

        // 关闭文档
        document.close();
    }

    private static void addTableHeader(PdfPTable table) {
        table.addCell(new PdfPCell(new Phrase("项目")));
        table.addCell(new PdfPCell(new Phrase("金额（元）")));
    }

    private static void addTableRow(PdfPTable table, String item, String amount) {
        table.addCell(new PdfPCell(new Phrase(item)));
        table.addCell(new PdfPCell(new Phrase(amount)));
    }

    private static String formatAmount(BigDecimal amount) {
        return amount != null ? amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString() : "0.00";
    }
}