package com.xuhao.payroll.service;

import com.xuhao.payroll.model.dto.LoginDTO;
import com.xuhao.payroll.model.vo.LoginVO;

/**
 * 登录服务接口
 */
public interface LoginService {
    /**
     * 登录
     * 
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    LoginVO login(LoginDTO loginDTO);

    /**
     * 退出登录
     */
    void logout();
}