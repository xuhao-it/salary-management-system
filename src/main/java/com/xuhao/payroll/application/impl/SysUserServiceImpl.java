package com.xuhao.payroll.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.xuhao.payroll.infrastructure.common.base.BaseServiceImpl;
import com.xuhao.payroll.infrastructure.common.constant.Constants;
import com.xuhao.payroll.mapper.SysUserMapper;
import com.xuhao.payroll.model.SysUser;
import com.xuhao.payroll.service.SysUserService;

/**
 * 系统用户Service实现类
 */
@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Integer> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    @Override
    public SysUser getByEmpId(Integer empId) {
        return sysUserMapper.selectByEmpId(empId);
    }

    @Override
    public List<SysUser> getByRoleType(Integer roleType) {
        return sysUserMapper.selectByRoleType(roleType);
    }

    @Override
    public List<SysUser> getByStatus(Integer status) {
        return sysUserMapper.selectByStatus(status);
    }

    @Override
    public boolean updateLastLoginTime(Integer userId) {
        return sysUserMapper.updateLastLoginTime(userId) > 0;
    }

    @Override
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword) {
        // 查询用户信息
        SysUser user = sysUserMapper.selectById(userId);
        if (user == null) {
            return false;
        }

        // 验证旧密码
        String oldPasswordMd5 = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        if (!user.getPassword().equals(oldPasswordMd5)) {
            return false;
        }

        // 更新新密码
        String newPasswordMd5 = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        return sysUserMapper.updatePassword(userId, newPasswordMd5) > 0;
    }

    @Override
    public boolean resetPassword(Integer userId) {
        // 重置为默认密码
        String defaultPassword = DigestUtils.md5DigestAsHex(Constants.DEFAULT_PASSWORD.getBytes());
        return sysUserMapper.updatePassword(userId, defaultPassword) > 0;
    }

    @Override
    public boolean enable(Integer userId) {
        SysUser user = new SysUser();
        user.setUserId(userId);
        user.setStatus(Constants.STATUS_ENABLED);
        return sysUserMapper.update(user) > 0;
    }

    @Override
    public boolean disable(Integer userId) {
        SysUser user = new SysUser();
        user.setUserId(userId);
        user.setStatus(Constants.STATUS_DISABLED);
        return sysUserMapper.update(user) > 0;
    }

    @Override
    public List<SysUser> getWithEmployee() {
        return sysUserMapper.selectWithEmployee();
    }
}