package com.xuhao.salary.infrastructure.persistence.entity;

import lombok.Data;

@Data
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;

    public String[] getRoles() {
        return new String[]{this.role};
    }
}
