package com.xuhao.payroll.mapper;

import com.xuhao.payroll.model.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统日志Mapper接口
 */
@Mapper
public interface SysLogMapper {
    /**
     * 插入日志
     * 
     * @param log 日志信息
     */
    void insert(SysLog log);

    /**
     * 批量删除日志
     * 
     * @param ids 日志ID列表
     */
    void deleteBatch(@Param("ids") Long[] ids);

    /**
     * 查询日志列表
     * 
     * @param username  用户名
     * @param operation 操作
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 日志列表
     */
    List<SysLog> queryList(@Param("username") String username,
            @Param("operation") String operation,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime);
}