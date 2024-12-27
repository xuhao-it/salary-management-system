package com.xuhao.salary.domain.service.auth;

import com.xuhao.salary.application.dto.request.auth.LoginRequest;
import com.xuhao.salary.application.dto.response.auth.LoginResponse;
import com.xuhao.salary.domain.entity.system.User;
import com.xuhao.salary.domain.repository.UserRepository;
import com.xuhao.salary.infrastructure.exception.BusinessException;
import com.xuhao.salary.infrastructure.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, 
                      JwtTokenProvider jwtTokenProvider, 
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }

        String token = jwtTokenProvider.generateToken(user.getUsername());
        userRepository.updateToken(user.getUsername(), token);

        return new LoginResponse(token, user.getRole(), user.getUsername());
    }
}
