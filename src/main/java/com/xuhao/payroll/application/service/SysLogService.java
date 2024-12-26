package com.xuhao.payroll.service;

import com.xuhao.payroll.model.SysLog;
import java.util.List;

/**
 * 系统日志服务接口
 */
public interface SysLogService {
    /**
     * 保存日志
     * 
     * @param log 日志信息
     */
    void save(SysLog log);

    /**
     * 批量删除日志
     * 
     * @param ids 日志ID列表
     */
    void deleteBatch(Long[] ids);

    /**
     * 查询日志列表
     * 
     * @param username  用户名
     * @param operation 操作
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 日志列表
     */
    List<SysLog> queryList(String username, String operation, String startTime, String endTime);
}