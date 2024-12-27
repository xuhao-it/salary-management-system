package com.xuhao.salary.domain.service.impl;

import com.xuhao.salary.domain.model.system.User;
import com.xuhao.salary.domain.repository.UserRepository;
import com.xuhao.salary.domain.service.interfaces.AuthDomainService;
import com.xuhao.salary.infrastructure.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDomainServiceImpl implements AuthDomainService {
    
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public User authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public String generateToken(String username) {
        return jwtTokenProvider.generateToken(username);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }
}
