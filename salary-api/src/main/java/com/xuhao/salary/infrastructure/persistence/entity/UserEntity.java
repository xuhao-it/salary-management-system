package com.xuhao.salary.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("users")
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    // 其他用户相关字段
}
