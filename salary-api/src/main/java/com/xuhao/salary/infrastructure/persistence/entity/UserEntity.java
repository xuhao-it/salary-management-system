package com.xuhao.salary.infrastructure.persistence.entity;

import lombok.Data;

@Data
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String roles;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
