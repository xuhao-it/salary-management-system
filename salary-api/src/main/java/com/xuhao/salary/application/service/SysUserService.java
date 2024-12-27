package com.xuhao.payroll.service;

import java.util.List;

import com.xuhao.payroll.infrastructure.common.base.BaseService;
import com.xuhao.payroll.model.SysUser;

/**
 * 系统用户Service接口
 */
public interface SysUserService extends BaseService<SysUser, Integer> {
    /**
     * 根据用户名查询
     * 
     * @param username 用户名
     * @return 用户信息
     */
    SysUser getByUsername(String username);

    /**
     * 根据员工ID查询
     * 
     * @param empId 员工ID
     * @return 用户信息
     */
    SysUser getByEmpId(Integer empId);

    /**
     * 根据角色类型查询
     * 
     * @param roleType 角色类型
     * @return 用户列表
     */
    List<SysUser> getByRoleType(Integer roleType);

    /**
     * 根据状态查询
     * 
     * @param status 状态
     * @return 用户列表
     */
    List<SysUser> getByStatus(Integer status);

    /**
     * 更新最后登录时间
     * 
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean updateLastLoginTime(Integer userId);

    /**
     * 更新密码
     * 
     * @param userId      用户ID
     * @param oldPassword 旧密���
     * @param newPassword 新密码
     * @return 是否成功
     */
    boolean updatePassword(Integer userId, String oldPassword, String newPassword);

    /**
     * 重置密码
     * 
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean resetPassword(Integer userId);

    /**
     * 启用用户
     * 
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean enable(Integer userId);

    /**
     * 禁用用户
     * 
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean disable(Integer userId);

    /**
     * 查询带员工信息的用户列表
     * 
     * @return 用户列表（包含员工信息）
     */
    List<SysUser> getWithEmployee();
}