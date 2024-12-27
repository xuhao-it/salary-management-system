package com.xuhao.payroll.mapper;

import java.time.LocalDate;
import java.util.List;

import com.xuhao.payroll.infrastructure.common.base.BaseMapper;
import com.xuhao.payroll.model.Reward;

/**
 * 奖惩记录Mapper接口
 */
public interface RewardMapper extends BaseMapper<Reward, Integer> {
    /**
     * 根据员工ID查询
     * 
     * @param empId 员工ID
     * @return 奖惩记录列表
     */
    List<Reward> selectByEmpId(Integer empId);

    /**
     * 根据类型查询
     * 
     * @param type 类型（1:奖励 2:处罚）
     * @return 奖惩记录列表
     */
    List<Reward> selectByType(Integer type);

    /**
     * 根据日期范围查询
     * 
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 奖惩记录列表
     */
    List<Reward> selectByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * 查询员工在指定月份的奖惩记录
     * 
     * @param empId 员工ID
     * @param year  年份
     * @param month 月份
     * @return 奖惩记录列表
     */
    List<Reward> selectByEmpIdAndMonth(Integer empId, Integer year, Integer month);

    /**
     * 查询带员工信息的奖惩记录列表
     * 
     * @return 奖惩记录列表（包含员工信息）
     */
    List<Reward> selectWithEmployee();
}