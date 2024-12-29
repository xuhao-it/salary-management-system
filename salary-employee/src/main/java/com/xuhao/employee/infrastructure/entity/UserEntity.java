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
    private Integer userId;  // 改为 Integer 以匹配数据库 INT 类型
    
    private String username;
    private String password;
    
    @Column(name = "emp_id")
    private Integer empId;   // 已经是 Integer，正确
    
    @Column(name = "role_type", columnDefinition = "TINYINT")
    private Byte roleType;  // 改为 Byte 类型以匹配 TINYINT
    
    @Column(name = "status", columnDefinition = "TINYINT")
    private Byte status;  // 改为 Byte 类型以匹配 TINYINT
    
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    // 删除未使用的字段:
    // private boolean enabled;
    // private boolean accountNonExpired;
    // private boolean credentialsNonExpired;
    // private boolean accountNonLocked;

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
        return status != null && status == 1;
    }

    public boolean isAccountNonExpired() {
        return status != null && status == 1;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return status != null && status == 1;
    }

    // 为了兼容之前的代码，添加getId方法
    public Integer getId() {
        return userId;
    }
}
