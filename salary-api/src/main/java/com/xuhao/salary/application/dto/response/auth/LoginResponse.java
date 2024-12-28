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
        private Long userId;
        private String username;
        private Long empId;
        private Integer roleType;
    }
}
