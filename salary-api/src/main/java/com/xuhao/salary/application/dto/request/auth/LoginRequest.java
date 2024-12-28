package com.xuhao.salary.application.dto.request.auth;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginRequest {
    private String username;
    private String password;
}
