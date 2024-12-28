package com.xuhao.salary.application.service.auth.impl;

import com.xuhao.salary.application.dto.request.auth.LoginRequest;
import com.xuhao.salary.application.dto.response.auth.LoginResponse;
import com.xuhao.salary.application.service.auth.AuthService;
import com.xuhao.salary.domain.service.AuthDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthDomainService authDomainService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return authDomainService.authenticate(loginRequest);
    }
}
