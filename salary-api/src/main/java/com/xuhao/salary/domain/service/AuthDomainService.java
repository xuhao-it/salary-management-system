package com.xuhao.salary.domain.service;

import com.xuhao.salary.application.dto.request.auth.LoginRequest;
import com.xuhao.salary.application.dto.response.auth.LoginResponse;

public interface AuthDomainService {
    LoginResponse authenticate(LoginRequest loginRequest);
}
