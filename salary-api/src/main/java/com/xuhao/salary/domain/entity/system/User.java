package com.xuhao.salary.domain.entity.system;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String companyName;
    private Integer status;
    private String token;
}
