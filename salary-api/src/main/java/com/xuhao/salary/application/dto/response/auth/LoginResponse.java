package com.xuhao.salary.application.dto.response.auth;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginResponse {
    private String token;
    private String username;
    private String role;
    private Long expiresIn;
}
