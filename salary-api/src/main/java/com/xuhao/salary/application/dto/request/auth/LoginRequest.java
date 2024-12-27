package com.xuhao.salary.application.dto.request.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
