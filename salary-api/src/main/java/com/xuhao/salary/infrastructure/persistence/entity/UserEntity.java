package com.xuhao.salary.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SysUser")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;  // 改为 Integer
    
    private String username;
    private String password;
    
    @Column(name = "emp_id")
    private Integer empId;   // 改为 Integer
    
    @Column(name = "role_type")
    private Integer roleType;
    
    private Integer status;
    
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;

    public String getRole() {
        if (roleType == null) return "ROLE_USER";
        return switch (roleType) {
            case 1 -> "ROLE_SUPER_ADMIN";
            case 2 -> "ROLE_ADMIN";
            case 3 -> "ROLE_HR";
            case 4 -> "ROLE_FINANCE";
            default -> "ROLE_USER";
        };
    }

    // Spring Security 所需的方法
    public boolean isEnabled() {
        return status == 1;
    }

    public boolean isAccountNonExpired() {
        return status == 1;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return status == 1;
    }

    // 为了兼容之前的代码，添加getId方法
    public Long getId() {
        return userId;
    }
}
