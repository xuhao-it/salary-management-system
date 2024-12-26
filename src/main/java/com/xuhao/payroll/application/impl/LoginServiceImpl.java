package com.xuhao.payroll.service.impl;

import com.xuhao.payroll.infrastructure.common.exception.BusinessException;
import com.xuhao.payroll.infrastructure.common.result.ResultCode;
import com.xuhao.payroll.model.SysUser;
import com.xuhao.payroll.model.dto.LoginDTO;
import com.xuhao.payroll.model.vo.LoginVO;
import com.xuhao.payroll.service.LoginService;
import com.xuhao.payroll.service.SysUserService;
import com.xuhao.payroll.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录服务实现类
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        // 验证用户名
        SysUser user = sysUserService.getByUsername(loginDTO.getUsername());
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        // 验证密码
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new BusinessException(ResultCode.LOGIN_FAILED);
        }

        // 验证用户状态
        if (user.getStatus() != 1) {
            throw new BusinessException(ResultCode.USER_DISABLED);
        }

        // 生成token
        String token = jwtUtil.generateToken(user.getUserId());

        // 构建返回数据
        LoginVO loginVO = new LoginVO();
        loginVO.setUserId(user.getUserId());
        loginVO.setUsername(user.getUsername());
        loginVO.setEmpId(user.getEmpId());
        loginVO.setRoleType(user.getRoleType());
        loginVO.setToken(token);

        // 设置员工信息
        if (user.getEmployee() != null) {
            loginVO.setEmpName(user.getEmployee().getEmpName());
            loginVO.setDeptName(user.getEmployee().getDeptName());
            loginVO.setPosition(user.getEmployee().getPosition());
            loginVO.setAvatar(user.getEmployee().getAvatar());
        }

        return loginVO;
    }

    @Override
    public void logout() {
        // TODO: 实现退出登录逻辑如清除token等
    }
}