package com.xuhao.payroll.mapper;

import java.time.LocalDate;
import java.util.List;

import com.xuhao.payroll.infrastructure.common.base.BaseMapper;
import com.xuhao.payroll.model.Attendance;

/**
 * 考勤记录Mapper接口
 */
public interface AttendanceMapper extends BaseMapper<Attendance, Integer> {
    /**
     * 根据员工ID查询
     * 
     * @param empId 员工ID
     * @return 考勤记录列表
     */
    List<Attendance> selectByEmpId(Integer empId);

    /**
     * 根据日期查询
     * 
     * @param date 日期
     * @return 考勤记录列表
     */
    List<Attendance> selectByDate(LocalDate date);

    /**
     * 根据员工ID和日期查询
     * 
     * @param empId 员工ID
     * @param date  日期
     * @return 考勤记录
     */
    Attendance selectByEmpIdAndDate(Integer empId, LocalDate date);

    /**
     * 根据日期范围查询
     * 
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 考勤记录列表
     */
    List<Attendance> selectByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * 查询带员工信息的考勤记录列表
     * 
     * @return 考勤记录列表（包含员工信息）
     */
    List<Attendance> selectWithEmployee();
}