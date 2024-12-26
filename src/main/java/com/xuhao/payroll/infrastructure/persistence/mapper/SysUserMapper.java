package com.xuhao.payroll.mapper;

import java.util.List;

import com.xuhao.payroll.infrastructure.common.base.BaseMapper;
import com.xuhao.payroll.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Mapper接口
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser, Integer> {
    /**
     * 根据用户名查询
     * 
     * @param username 用户名
     * @return 用户信息
     */
    SysUser selectByUsername(String username);

    /**
     * 根据员工ID查询
     * 
     * @param empId 员工ID
     * @return 用户信息
     */
    SysUser selectByEmpId(Integer empId);

    /**
     * 根据角色类型查询
     * 
     * @param roleType 角色类型
     * @return 用户列表
     */
    List<SysUser> selectByRoleType(Integer roleType);

    /**
     * 根据状态查询
     * 
     * @param status 状态
     * @return 用户列表
     */
    List<SysUser> selectByStatus(Integer status);

    /**
     * 更新最后登录时间
     * 
     * @param userId 用户ID
     * @return 影响行数
     */
    int updateLastLoginTime(Integer userId);

    /**
     * 更新密码
     * 
     * @param userId   用户ID
     * @param password 新密码
     * @return 影响行数
     */
    int updatePassword(Integer userId, String password);

    /**
     * 查询带员工信息的用户列表
     * 
     * @return 用户列表（包含员工信息）
     */
    List<SysUser> selectWithEmployee();

    SysUser selectById(Integer id);
    int update(SysUser user);
}