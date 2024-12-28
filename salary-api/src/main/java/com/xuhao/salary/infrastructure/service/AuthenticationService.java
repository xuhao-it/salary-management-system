package com.xuhao.salary.infrastructure.service;

import com.xuhao.salary.application.dto.request.auth.LoginRequest;
import com.xuhao.salary.application.dto.response.auth.LoginResponse;
import com.xuhao.salary.infrastructure.persistence.entity.UserEntity;
import com.xuhao.salary.infrastructure.persistence.mapper.UserMapper;
import com.xuhao.salary.infrastructure.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        String token = jwtTokenProvider.generateToken(authentication);
        UserEntity user = userMapper.findByUsername(request.getUsername());
        
        return createLoginResponse(token, user);
    }

    private LoginResponse createLoginResponse(String token, UserEntity user) {
        LoginResponse.UserInfo userInfo = LoginResponse.UserInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();

        return LoginResponse.builder()
                .token(token)
                .userInfo(userInfo)
                .build();
    }
}
