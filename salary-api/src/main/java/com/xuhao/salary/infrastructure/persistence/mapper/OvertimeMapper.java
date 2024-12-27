package com.xuhao.payroll.mapper;

import java.time.LocalDate;
import java.util.List;

import com.xuhao.payroll.infrastructure.common.base.BaseMapper;
import com.xuhao.payroll.model.Overtime;

/**
 * 加班记录Mapper接口
 */
public interface OvertimeMapper extends BaseMapper<Overtime, Integer> {
    /**
     * 根据员工ID查询
     * 
     * @param empId 员工ID
     * @return 加班记录列表
     */
    List<Overtime> selectByEmpId(Integer empId);

    /**
     * 根据审批人ID查询
     * 
     * @param approverId 审批人ID
     * @return 加班记录列表
     */
    List<Overtime> selectByApproverId(Integer approverId);

    /**
     * 根据状态查询
     * 
     * @param status 状态
     * @return 加班记录列表
     */
    List<Overtime> selectByStatus(Integer status);

    /**
     * 根据日期范围查询
     * 
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 加班记录列表
     */
    List<Overtime> selectByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * 查询待审批的加班记录
     * 
     * @param approverId 审批人ID
     * @return 待审批���加班记录列表
     */
    List<Overtime> selectPendingByApproverId(Integer approverId);

    /**
     * 查询带员工和审批人信息的加班记录列表
     * 
     * @return 加班记录列表（包含员工和审批人信息）
     */
    List<Overtime> selectWithEmployeeAndApprover();
}