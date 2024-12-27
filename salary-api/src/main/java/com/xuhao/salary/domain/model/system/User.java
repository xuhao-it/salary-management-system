package com.xuhao.salary.domain.model.system;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String companyName;
    private Integer status;
    private String token;
}
