package com.xuhao.salary.domain.model.system;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "emp_id")
    private Long empId;
    
    @Column(name = "role_type")
    private Integer roleType;
    
    private Integer status;
    
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    
    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
        if (status == null) {
            status = 1;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }

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
}
