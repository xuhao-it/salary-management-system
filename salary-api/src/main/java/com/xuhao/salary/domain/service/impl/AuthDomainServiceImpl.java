package com.xuhao.salary.domain.service.impl;

import com.xuhao.salary.application.dto.request.auth.LoginRequest;
import com.xuhao.salary.application.dto.response.auth.LoginResponse;
import com.xuhao.salary.domain.service.AuthDomainService;
import org.springframework.stereotype.Service;

@Service
public class AuthDomainServiceImpl implements AuthDomainService {
    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        // 实现认证逻辑
        return null;
    }
}

