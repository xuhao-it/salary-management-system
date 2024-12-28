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
        LoginResponse.UserInfo userInfo = buildUserInfo(user);

        return LoginResponse.builder()
                .token(token)
                .userInfo(userInfo)
                .build();
    }

    private LoginResponse.UserInfo buildUserInfo(UserEntity user) {
        return LoginResponse.UserInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole()) // 修改为role而不是roles
                .build();
    }
}
