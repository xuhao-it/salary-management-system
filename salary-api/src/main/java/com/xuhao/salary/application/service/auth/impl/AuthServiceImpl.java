package com.xuhao.salary.application.service.auth.impl;

import com.xuhao.salary.application.dto.request.auth.LoginRequest;
import com.xuhao.salary.application.dto.response.auth.LoginResponse;
import com.xuhao.salary.application.service.auth.AuthService;
import com.xuhao.salary.domain.model.system.User;
import com.xuhao.salary.domain.service.interfaces.AuthDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final AuthDomainService authDomainService;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = authDomainService.authenticate(request.getUsername(), request.getPassword());
        String token = authDomainService.generateToken(user.getUsername());
        
        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .build();
    }
}
