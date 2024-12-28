package com.xuhao.salary.application.service.auth.impl;

import com.xuhao.salary.application.dto.request.auth.LoginRequest;
import com.xuhao.salary.application.dto.response.auth.LoginResponse;
import com.xuhao.salary.application.service.auth.AuthService;
import com.xuhao.salary.domain.service.interfaces.AuthDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final AuthDomainService authDomainService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticate(request.getUsername(), request.getPassword());
        
        String token = authDomainService.generateToken(request.getUsername());
        return LoginResponse.builder()
                .token(token)
                .username(request.getUsername())
                .build();
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
    }
}
