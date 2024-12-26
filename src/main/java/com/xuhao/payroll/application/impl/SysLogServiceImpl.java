package com.xuhao.payroll.service.impl;

import com.xuhao.payroll.mapper.SysLogMapper;
import com.xuhao.payroll.model.SysLog;
import com.xuhao.payroll.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统日志服务实现类
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLog log) {
        sysLogMapper.insert(log);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        sysLogMapper.deleteBatch(ids);
    }

    @Override
    public List<SysLog> queryList(String username, String operation, String startTime, String endTime) {
        return sysLogMapper.queryList(username, operation, startTime, endTime);
    }
}