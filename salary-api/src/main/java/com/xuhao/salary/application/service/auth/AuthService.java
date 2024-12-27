package com.xuhao.salary.application.service.auth;

import com.xuhao.salary.application.dto.request.auth.LoginRequest;
import com.xuhao.salary.application.dto.response.auth.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
