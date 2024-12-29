package com.xuhao.salary.application.dto.response.auth;

import com.xuhao.salary.domain.model.system.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private UserInfo userInfo;

    @Data
    @Builder
    public static class UserInfo {
        private String username;
        private UserRole role;  // 修改为UserRole类型
    }
}