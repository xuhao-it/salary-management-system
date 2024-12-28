package com.xuhao.salary.domain.service.impl;

import com.xuhao.salary.domain.model.system.User;
import com.xuhao.salary.domain.repository.UserRepository;
import com.xuhao.salary.domain.service.interfaces.AuthDomainService;
import com.xuhao.salary.infrastructure.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class AuthDomainServiceImpl implements AuthDomainService {
    
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        return user;
    }

    @Override
    public String generateToken(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }
}

