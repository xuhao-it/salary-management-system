package com.xuhao.payroll.mapper;

import java.time.LocalDateTime;
import java.util.List;

import com.xuhao.payroll.infrastructure.common.base.BaseMapper;
import com.xuhao.payroll.model.Leave;

/**
 * 请假记录Mapper接口
 */
public interface LeaveMapper extends BaseMapper<Leave, Integer> {
    /**
     * 根据员工ID查询
     * 
     * @param empId 员工ID
     * @return 请假记录列表
     */
    List<Leave> selectByEmpId(Integer empId);

    /**
     * 根据审批人ID查询
     * 
     * @param approverId 审批人ID
     * @return 请假记录列表
     */
    List<Leave> selectByApproverId(Integer approverId);

    /**
     * 根据状态查询
     * 
     * @param status 状态
     * @return 请假记录列表
     */
    List<Leave> selectByStatus(Integer status);

    /**
     * 根据时间范围查询
     * 
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 请假记录列表
     */
    List<Leave> selectByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 查询待审批的请假记录
     * 
     * @param approverId 审批人ID
     * @return 待审批的请假���录列表
     */
    List<Leave> selectPendingByApproverId(Integer approverId);

    /**
     * 查询带员工和审批人信息的请假记录列表
     * 
     * @return 请假记录列表（包含员工和审批人信息）
     */
    List<Leave> selectWithEmployeeAndApprover();
}