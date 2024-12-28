package com.xuhao.salary.application.dto.response.auth;

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
        private Integer id;  // 修改为Integer以匹配UserEntity
        private String username;
        private String email;
        private Integer roleType;  // 修改为Integer类型
    }
}
